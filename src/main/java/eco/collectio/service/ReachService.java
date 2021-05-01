package eco.collectio.service;

import eco.collectio.domain.Join;
import eco.collectio.domain.Reach;
import eco.collectio.domain.Stage;
import eco.collectio.domain.User;
import eco.collectio.repository.ReachRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReachService {
    private final ReachRepository reachRepository;
    private final StageService stageService;
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(ReachService.class);

    @Autowired
    public ReachService(ReachRepository reachRepository, StageService stageService, UserService userService) {
        this.reachRepository = reachRepository;
        this.stageService = stageService;
        this.userService = userService;
    }

    private Reach upsert(Long userId, Long stageId) {
        Reach result = reachRepository.findByNodesIds(userId, stageId);
        if (result == null) {
            Optional<User> persistedUser = userService.getById(userId);
            Optional<Stage> persistedStage = stageService.getById(stageId);
            if (!persistedUser.isPresent() || !persistedStage.isPresent()) {
                return null;
            }
            Reach newReach = new Reach(persistedUser.get(), persistedStage.get());
            return reachRepository.save(newReach);
        }
        result.reachievedStage();
        return reachRepository.save(result);
    }

    public Reach checkCompletedStage(Join join) {
        int currentWeeks = (int) ChronoUnit.WEEKS.between(join.getStartedAt(), join.getLastChecked());
        List<Stage> orderedStagesDesc = stageService.getAllByChallengeId(join.getChallenge().getId());
        if (orderedStagesDesc == null || orderedStagesDesc.isEmpty()) {
            logger.error("Challenge does not have stages");
            return null;
        }
        for (Stage stage : orderedStagesDesc) {
            if (currentWeeks == stage.getWeeksCondition()) {
                hideActiveBadge(join);
                return upsert(join.getUser().getId(), stage.getId());
            }
        }
        return null;
    }

    private void hideActiveBadge(Join join) {
        Reach updatedReach = reachRepository.hideActiveBadgeFromChallenge(join.getUser().getId(), join.getChallenge().getId());
        if (updatedReach != null && updatedReach.getShow()) {
            logger.error("Reach updated fail");
        }
    }
}

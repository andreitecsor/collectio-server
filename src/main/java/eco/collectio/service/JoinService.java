package eco.collectio.service;

import eco.collectio.domain.*;
import eco.collectio.exception.InvalidPostException;
import eco.collectio.repository.JoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class JoinService {
    private final JoinRepository joinRepository;
    private final UserService userService;
    private final ChallengeService challengeService;
    private final ReachService reachService;
    private final PostService postService;
    private Logger logger = LoggerFactory.getLogger(JoinService.class);

    @Autowired
    public JoinService(JoinRepository joinRepository, UserService userService, ChallengeService challengeService, ReachService reachService, PostService postService) {
        this.joinRepository = joinRepository;
        this.userService = userService;
        this.challengeService = challengeService;
        this.reachService = reachService;
        this.postService = postService;
    }

    public List<Join> getAll() {
        return joinRepository.findAll();
    }

    public List<Join> getAllActives(Long userId) {
        return joinRepository.findAllActives(userId);
    }

    public Join getByNodesIds(Long userId, Long challengeId) {
        return joinRepository.findByNodesIds(userId, challengeId);
    }

    public Join upsert(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null) {
            Optional<User> persistedUser = userService.getById(userId);
            Optional<Challenge> persistedChallenge = challengeService.getById(challengeId);
            if (!persistedUser.isPresent() || !persistedChallenge.isPresent()) {
                logger.error("user(id=" + userId + ") or challenge(id=" + challengeId + ") does not exists");
                return null;
            }
            Join join = new Join(persistedUser.get(), persistedChallenge.get());
            try {
                createChallengePost(join.getUser(), join.getChallenge());
                return joinRepository.save(join);
            } catch (InvalidPostException e) {
                e.printStackTrace();
            }
        }
        Join joinUpdate = joinUpdate(result);
        if (joinUpdate != null) {
            return joinUpdate;
        }
        logger.error(result.toString() + " is still active");
        return null;
    }

    private Join joinUpdate(Join result) {
        if (result.getEndedAt() != null) {
            try {
                result.restartChallenge();
                createChallengePost(result.getUser(), result.getChallenge());
                return joinRepository.save(result);
            } catch (InvalidPostException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void createChallengePost(User user, Challenge challenge) throws InvalidPostException {
        postService.upsert(new Post.PostBuilder(PostType.CHALLENGE, user)
                .setChallenge(challenge)
                .build());
    }

    public Join endChallenge(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            return null;
        }
        result.endChallenge();
        return joinRepository.save(result);
    }

    public Join checkChallengeActivity(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            return null;
        }
        int daysBetween = (int) ChronoUnit.DAYS.between(result.getLastChecked(), LocalDate.now());
        if (daysBetween < 4) {
            return null; //trust days period -> you cant check now
        }
        if (daysBetween < 7) {
            result.checkChallenge();
            reachService.checkCompletedStage(result);
            return joinRepository.save(result);
        }
        return endChallenge(userId, challengeId);
    }
}

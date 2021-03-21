package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.Join;
import eco.collectio.domain.User;
import eco.collectio.repository.JoinRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JoinService {
    private final JoinRepository joinRepository;
    private final UserService userService;
    private final ChallengeService challengeService;

    public JoinService(JoinRepository joinRepository, UserService userService, ChallengeService challengeService) {
        this.joinRepository = joinRepository;
        this.userService = userService;
        this.challengeService = challengeService;
    }

    /**
     * @return all JOINED relationships from database
     */
    public List<Join> get() {
        return joinRepository.findAll();
    }

    /**
     * @param userId      who joined a challenge
     * @param challengeId challenged being joined
     * @return a JOINED relationship based on params from database
     */
    public Join getByNodesIds(Long userId, Long challengeId) {
        return joinRepository.findByNodesIds(userId, challengeId);
    }

    /**
     * @param userId who we want to find out his active challenges
     * @return all JOINED relationship based on param where endedAt is null from database
     */
    public List<Join> getAllActives(Long userId) {
        return joinRepository.findAllActives(userId);
    }

    /**
     * @param userId      who joined/wants to join a challenge
     * @param challengeId the challenge joined in the past or wants to join
     * @return the new created JOINED relationship or the updated JOINED relationship
     * only if endedAt is not null, else returns null
     */
    public Join startRestartChallenge(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null) {
            Optional<User> persistedUser = userService.getById(userId);
            Optional<Challenge> persistedChallenge = challengeService.getById(challengeId);
            if (!persistedUser.isPresent() || !persistedChallenge.isPresent()) {
                System.err.println("user or challenge does not exists");
                return null;
            }
            Join join = new Join(LocalDateTime.now(), persistedUser.get(), persistedChallenge.get(), 1);
            return joinRepository.save(join);
        }
        if (result.getEndedAt() != null) {
            result.restartChallenge();
            return joinRepository.save(result);
        }
        System.err.println(result.toString() + " is still active");
        return null;
    }

    /**
     * @param userId      who wants to quit a challenge
     * @param challengeId the challenge which the users wat
     * @return the updated relationship if the relationships exists and endedAt is null, else returns null
     */
    public Join endChallenge(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            return null;
        }
        result.endChallenge();
        return joinRepository.save(result);
    }

}

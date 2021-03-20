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

    public List<Join> get() {
        return joinRepository.findAll();
    }

    public Join getByNodesIds(Long userId, Long challengeId) {
        return joinRepository.findByNodesIds(userId, challengeId);
    }

    public List<Join> getAllActives(Long userId) {
        return joinRepository.findAllActives(userId);
    }

    public Join startRestartChallenge(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null) {
            Optional<User> persistedUser = userService.get(userId);
            Optional<Challenge> persistedChallenge = challengeService.get(challengeId);
            if (!persistedUser.isPresent() || !persistedChallenge.isPresent()) {
                return null;
            }
            Join join = new Join(LocalDateTime.now(), persistedUser.get(), persistedChallenge.get(), 1);
            return joinRepository.save(join);
        }
        result.restartChallenge();
        return joinRepository.save(result);
    }

    public Join endChallenge(Long userId, Long challengeId) {
        Join result = getByNodesIds(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            return null;
        }
        result.endChallenge();
        return joinRepository.save(result);
    }

}

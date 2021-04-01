package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository repository) {
        this.challengeRepository = repository;
    }

    public List<Challenge> getAll() {
        return challengeRepository.findAll();
    }

    public Optional<Challenge> getById(Long id) {
        return challengeRepository.findById(id);
    }

    public Challenge create(Challenge challenge) {
        Challenge sameTitleChallenge = challengeRepository.findByTitle(challenge.getTitle());
        if (challenge.getTitle() == null || sameTitleChallenge.equals(challenge)) {
            return null;
        }
        return challengeRepository.save(challenge);
    }
}

package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.repository.ChallengeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ChallengeService.class);


    @Autowired
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
        if (challenge.getTitle() == null) {
            return null;
        }
        if (sameTitleChallenge != null && sameTitleChallenge.equals(challenge)) {
            return null;
        }
        return challengeRepository.save(challenge);
    }

    public Challenge update(Long id, Challenge newChallengeDetails) {
        if (newChallengeDetails == null || newChallengeDetails.getTitle() == null) {
            LOGGER.error("Invalid challenge to update");
            return null;
        }
        Optional<Challenge> optionalChallenge = getById(id);
        if (!optionalChallenge.isPresent()) {
            LOGGER.error("There is no challenge to update");
            return null;
        }
        Challenge challengeToUpdate = optionalChallenge.get();
        challengeToUpdate.setTitle(newChallengeDetails.getTitle());
        return challengeRepository.save(challengeToUpdate);
    }
}

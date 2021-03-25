package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository repository;

    public ChallengeService(ChallengeRepository repository) {
        this.repository = repository;
    }

    /**
     * @return all challenges from database
     */
    public List<Challenge> get() {
        return repository.findAll();
    }

    /**
     * @param id challenge's unique id
     * @return specific user from database via id
     */
    public Optional<Challenge> getById(Long id) {
        return repository.findById(id);
    }

    /**
     * @param challenge's properties without id
     * @return the new challenge added or null if: challenge is null or it's data types are incorrect
     */
    public Challenge create(Challenge challenge) {
        //TODO:Should check if the user already exists based on email.
        if (challenge == null || challenge.getTitle() == null) {
            return null;
        }
        return repository.save(challenge);
    }
}

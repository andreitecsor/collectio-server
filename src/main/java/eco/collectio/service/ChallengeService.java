package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.Join;
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

    public List<Challenge> get() {
        return repository.findAll();
    }

    public Optional<Challenge> get(Long id) {
        return repository.findById(id);
    }

    public Challenge create(Challenge challenge) {
        return repository.save(challenge);
    }
}

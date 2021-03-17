package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    private final ChallengeRepository repository;

    public ChallengeService(ChallengeRepository repository) {
        this.repository = repository;
    }

    public List<Challenge> getAll() {
        return repository.findAll();
    }
}

package eco.collectio.service;

import eco.collectio.domain.Stage;
import eco.collectio.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    private final StageRepository stageRepository;

    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public List<Stage> getAllByChallenge(Long challengeId) {
        return stageRepository.findAllByChallenge(challengeId);
    }

    public Optional<Stage> getById(Long stageId) {
        return stageRepository.findById(stageId);
    }
}

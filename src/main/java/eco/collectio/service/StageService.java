package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.Stage;
import eco.collectio.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    private final StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public List<Stage> getAllByChallengeId(Long challengeId) {
        return stageRepository.findAllByChallenge(challengeId);
    }

    public Optional<Stage> getById(Long stageId) {
        return stageRepository.findById(stageId);
    }

    public Challenge findChallenge(Stage stage) {
        return stageRepository.findChallenge(stage.getId());
    }
}

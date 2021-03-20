package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.Influence;
import eco.collectio.domain.User;
import eco.collectio.repository.InfluenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InfluenceService {
    private final InfluenceRepository influenceRepository;
    private final UserService userService;
    private final ChallengeService challengeService;

    public InfluenceService(InfluenceRepository repository, UserService userService, ChallengeService challengeService) {
        this.influenceRepository = repository;
        this.userService = userService;
        this.challengeService = challengeService;
    }

    public List<Influence> get() {
        return influenceRepository.findAll();
    }

    public Influence create(Long whoInfluencedId, Long whoIsInfluencedId, Long challengeId) {
        Optional<User> whoInfluenced = userService.get(whoInfluencedId);
        Optional<User> whoIsInfluenced = userService.get(whoIsInfluencedId);
        Optional<Challenge> challenge = challengeService.get(challengeId);
        if (!whoInfluenced.isPresent() || !whoIsInfluenced.isPresent() || !challenge.isPresent()) {
            return null;
        }
        Influence influence = new Influence(whoInfluenced.get(), whoIsInfluenced.get(), challengeId, LocalDateTime.now());
        return influenceRepository.save(influence);
    }
}

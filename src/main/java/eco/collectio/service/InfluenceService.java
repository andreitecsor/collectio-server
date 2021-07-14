package eco.collectio.service;

import eco.collectio.domain.Influence;
import eco.collectio.domain.User;
import eco.collectio.repository.InfluenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfluenceService {
    private final InfluenceRepository influenceRepository;
    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(InfluenceService.class);

    @Autowired
    public InfluenceService(InfluenceRepository repository, UserService userService) {
        this.influenceRepository = repository;
        this.userService = userService;
    }

    public List<Influence> get() {
        return influenceRepository.findAll();
    }

    public Influence upsert(String whoInfluencedId, String whoIsInfluencedId) {
        Influence result = influenceRepository.findByNodesIds(whoInfluencedId, whoIsInfluencedId);
        if (result == null) {
            Optional<User> whoInfluenced = userService.getById(whoInfluencedId);
            Optional<User> whoIsInfluenced = userService.getById(whoIsInfluencedId);
            if (!whoInfluenced.isPresent() || !whoIsInfluenced.isPresent()) {
                LOGGER.error("user whoInfluenced(id=" + whoInfluencedId + ") or user whoIsInfluenced(id=" + whoIsInfluencedId + ") does not exists");
                return null;
            }
            Influence newInfluenceRelation = new Influence(whoInfluenced.get(), whoIsInfluenced.get());
            return influenceRepository.save(newInfluenceRelation);
        }
        result.increaseInfluence();
        return influenceRepository.save(result);
    }
}

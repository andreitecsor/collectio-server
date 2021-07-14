package eco.collectio.controller;

import eco.collectio.domain.Challenge;
import eco.collectio.service.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;
    private final Logger LOGGER = LoggerFactory.getLogger(ChallengeController.class);

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Challenge>> getAll() {
        List<Challenge> result = challengeService.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getById(@PathVariable Long id) {
        Optional<Challenge> result = challengeService.getById(id);
        return result.map(challenge -> ResponseEntity.ok().body(challenge)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("")
    public ResponseEntity<Challenge> add(@RequestBody Challenge challenge) {
        Challenge result = challengeService.create(challenge);
        if (result == null) {
            LOGGER.error("challenge already exists or its attributes does not match the default or have null values");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Challenge> update(@PathVariable Long id, @RequestBody Challenge challenge) {
        Challenge result = challengeService.update(id, challenge);
        if (result == null) {
            LOGGER.error("challenge already exists or its attributes does not match the default or have null values");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

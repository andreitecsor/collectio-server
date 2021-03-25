package eco.collectio.controller;

import eco.collectio.domain.Challenge;
import eco.collectio.service.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private final ChallengeService service;

    private Logger logger = LoggerFactory.getLogger(ChallengeController.class);

    public ChallengeController(ChallengeService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity get() {
        List<Challenge> result = service.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody Challenge challenge) {
        Challenge result = service.create(challenge);
        if (result == null) {
            logger.error("challenge attributes does not match the default or have null values");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

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

@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {
    private final ChallengeService service;
    private Logger logger = LoggerFactory.getLogger(ChallengeController.class);

    @Autowired
    public ChallengeController(ChallengeService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity get() {
        List<Challenge> result = service.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody Challenge challenge) {
        Challenge result = service.create(challenge);
        if (result == null) {
            logger.error("challenge already exists or its attributes does not match the default or have null values");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body or challenge may already exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

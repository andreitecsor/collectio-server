package eco.collectio.controller;

import eco.collectio.domain.Challenge;
import eco.collectio.service.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private final ChallengeService service;

    public ChallengeController(ChallengeService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Challenge>> getAll() {
        List<Challenge> result = service.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<Challenge> create(@RequestBody Challenge challenge) {
        Challenge result = service.create(challenge);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

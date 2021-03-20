package eco.collectio.controller;

import eco.collectio.domain.Join;
import eco.collectio.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("joined")
public class JoinController {
    private final JoinService service;

    public JoinController(JoinService joinService) {
        this.service = joinService;
    }

    /**
     * Getting all JOINED relationship
     */
    @GetMapping("")
    public ResponseEntity<List<Join>> get() {
        List<Join> result = service.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * Getting all active(endedAt is null) JOINED relationship
     */
    @GetMapping("/active/{userId}")
    public ResponseEntity<List<Join>> get(@PathVariable Long userId) {
        List<Join> result = service.getAllActives(userId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * Getting JOINED relationship based on userId and challengeId
     */
    @GetMapping("/{userId}-{challengeId}")
    public ResponseEntity<Join> get(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = service.getByNodesIds(userId, challengeId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * Create JOINED relationship based on userId and challengeId if not already exists.
     * If exists and endedAt is not null -> reset startedAt, lastChecked, endedAt and increment timesTried
     * TODO:Should return specific error.
     */
    @PutMapping("/{userId}-{challengeId}")
    public ResponseEntity<Join> upsert(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = service.startRestartChallenge(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * End JOINED relationship based on userId and challengeId if exists and endedAt is null;
     * Else does nothing.
     * TODO:Should return specific error.
     */
    @PutMapping("/end/{userId}-{challengeId}")
    public ResponseEntity<Join> endChallenge(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = service.endChallenge(userId, challengeId);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

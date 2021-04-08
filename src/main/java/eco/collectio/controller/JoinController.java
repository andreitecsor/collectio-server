package eco.collectio.controller;

import eco.collectio.domain.Influence;
import eco.collectio.domain.Join;
import eco.collectio.service.InfluenceService;
import eco.collectio.service.JoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("joined")
public class JoinController {
    private final JoinService joinService;
    private final InfluenceService influenceService;

    private Logger logger = LoggerFactory.getLogger(JoinController.class);

    public JoinController(JoinService joinService, InfluenceService influenceService) {
        this.joinService = joinService;
        this.influenceService = influenceService;
    }

    /**
     * Getting all JOINED relationship
     */
    @GetMapping("")
    public ResponseEntity get() {
        List<Join> result = joinService.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * Getting all active(endedAt is null) JOINED relationship
     */
    @GetMapping("/active/{userId}")
    public ResponseEntity get(@PathVariable Long userId) {
        List<Join> result = joinService.getAllActives(userId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * Getting a JOINED relationship based on userId and challengeId
     */
    @GetMapping("/{userId}-{challengeId}")
    public ResponseEntity get(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = joinService.getByNodesIds(userId, challengeId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * Create JOINED relationship based on userId and challengeId if not already exists.
     * If exists and endedAt is not null -> reset startedAt, lastChecked, endedAt and increment timesTried
     */
    @PutMapping("/{userId}-{challengeId}")
    public ResponseEntity add(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = joinService.upsert(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            logger.error(" JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    " does not exist or it's still active ");
            return ResponseEntity.badRequest().body("Invalid path variables");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * Create JOINED relationship influenced by other user.
     * If exists and endedAt is not null -> reset startedAt, lastChecked, endedAt and increment timesTried
     */
    @PutMapping("/{userId}-{challengeId}/influenced={influencerId}")
    public ResponseEntity addByInfluenced(@PathVariable Long userId,
                                          @PathVariable Long challengeId,
                                          @PathVariable Long influencerId) {
        Join result = joinService.upsert(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            logger.error("JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    ", influencerId= " + influencerId +
                    " does not exist or it's still active. ");
            return ResponseEntity.badRequest().body("Invalid path variables");
        }
        Influence influence = influenceService.upsert(influencerId, userId);

        logger.info("Influence relationship created" + influence);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * End JOINED relationship based on userId and challengeId if exists and endedAt is null;
     */
    @PutMapping("/end/{userId}-{challengeId}")
    public ResponseEntity endChallenge(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = joinService.endChallenge(userId, challengeId);
        if (result == null) {
            logger.error("JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    " does not exist or it's already ended. ");
            return ResponseEntity.badRequest().body("Invalid path variables");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Check JOINED relationship based on userId and challengeId;
     */
    @PutMapping("/check/{userId}-{challengeId}")
    public ResponseEntity checkChallenge(@PathVariable Long userId, @PathVariable Long challengeId) {
        Join result = joinService.checkChallengeActivity(userId, challengeId);
        if (result == null) {
            logger.error("JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    " is still in trust days or does not exist/it's already ended.");
            return ResponseEntity.badRequest().body("JOINED relationships cannot be updated right now or does not exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}

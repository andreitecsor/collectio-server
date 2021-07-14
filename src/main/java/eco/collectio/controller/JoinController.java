package eco.collectio.controller;

import eco.collectio.domain.Influence;
import eco.collectio.domain.Join;
import eco.collectio.dto.Achievement;
import eco.collectio.service.InfluenceService;
import eco.collectio.service.JoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/joined")
public class JoinController {
    private final JoinService joinService;
    private final InfluenceService influenceService;
    private final Logger LOGGER = LoggerFactory.getLogger(JoinController.class);

    @Autowired
    public JoinController(JoinService joinService, InfluenceService influenceService) {
        this.joinService = joinService;
        this.influenceService = influenceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Join>> getAll() {
        List<Join> result = joinService.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/actives/{userId}")
    public ResponseEntity<List<Join>> getAllActives(@PathVariable String userId) {
        List<Join> result = joinService.getAllActives(userId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/achievements/{userId}")
    public ResponseEntity<List<Achievement>> getAllAchievements(@PathVariable String userId) {
        List<Achievement> result = joinService.getAllAchievements(userId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{userId}->{challengeId}")
    public ResponseEntity<Join> getByNodesIds(@PathVariable String userId, @PathVariable Long challengeId) {
        Join result = joinService.getByNodesIds(userId, challengeId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{userId}->{challengeId}")
    public ResponseEntity<Join> add(@PathVariable String userId, @PathVariable Long challengeId) {
        Join result = joinService.upsert(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            LOGGER.error(" JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    " does not exist or it's still active ");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{userId}->{challengeId}/influencedBy={influencerId}")
    public ResponseEntity<Join> addByInfluenced(@PathVariable String userId,
                                                @PathVariable Long challengeId,
                                                @PathVariable String influencerId) {
        Join result = joinService.upsert(userId, challengeId);
        if (result == null || result.getEndedAt() != null) {
            LOGGER.error("JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    ", influencerId= " + influencerId +
                    " does not exist or it's still active. ");
            return ResponseEntity.badRequest().build();
        }
        Influence influence = influenceService.upsert(influencerId, userId);

        LOGGER.info("Influence relationship created" + influence);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/end/{userId}->{challengeId}")
    public ResponseEntity<Join> endChallenge(@PathVariable String userId, @PathVariable Long challengeId) {
        Join result = joinService.endChallenge(userId, challengeId);
        if (result == null) {
            LOGGER.error("JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    " does not exist or it's already ended. ");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/check/{userId}->{challengeId}")
    public ResponseEntity<Join> checkChallenge(@PathVariable String userId, @PathVariable Long challengeId) {
        Join result = joinService.checkChallengeActivity(userId, challengeId);
        if (result == null) {
            LOGGER.error("JOINED relationship requested with userId= " + userId +
                    ", challengeId= " + challengeId +
                    " is still in trust days or does not exist/it's already ended.");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}

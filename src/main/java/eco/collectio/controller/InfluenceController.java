package eco.collectio.controller;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.Influence;
import eco.collectio.domain.Join;
import eco.collectio.service.ChallengeService;
import eco.collectio.service.InfluenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/influenced")
public class InfluenceController {
    private final InfluenceService service;

    public InfluenceController(InfluenceService service) {
        this.service = service;
    }

    /**
     * Getting all INFLUENCED relationship
     */
    @GetMapping("")
    public ResponseEntity<List<Influence>> get() {
        List<Influence> result = service.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

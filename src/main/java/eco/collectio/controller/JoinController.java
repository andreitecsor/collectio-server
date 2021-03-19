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

    @GetMapping("")
    public ResponseEntity<List<Join>> getAll() {
        List<Join> result = service.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{userId}-{challengeId}")
    public ResponseEntity<Join> create(@PathVariable Long userId,@PathVariable Long challengeId) {
        Join result = service.upsertRelation(userId,challengeId);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

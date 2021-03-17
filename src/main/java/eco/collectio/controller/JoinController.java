package eco.collectio.controller;

import eco.collectio.domain.Join;
import eco.collectio.domain.User;
import eco.collectio.service.JoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("joined")
public class JoinController {
    private final JoinService service;

    public JoinController(JoinService joinService) {
        this.service = joinService;
    }

    @GetMapping("")
    public ResponseEntity<List<Join>> getAllByChallenge() {
        List<Join> result = service.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

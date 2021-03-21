package eco.collectio.controller;

import eco.collectio.domain.Influence;
import eco.collectio.service.InfluenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

package eco.collectio.controller;

import eco.collectio.domain.relationship.Influence;
import eco.collectio.service.InfluenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r/influence")
public class InfluenceController {
    private final InfluenceService service;

    public InfluenceController(InfluenceService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Influence> add(@RequestBody Influence influence) {
        Influence result = service.create(influence);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<Influence>> getAll() {
        List<Influence> result = service.getAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Influence> getById(@PathVariable Long id) {
        Influence result = service.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Influence> update(@RequestBody Influence influence) {
        Influence result = service.update(influence);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Influence> deleteById(@PathVariable Long id) {
        Influence result = service.delete(id);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

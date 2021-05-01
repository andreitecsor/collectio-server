package eco.collectio.controller;

import eco.collectio.domain.Influence;
import eco.collectio.service.InfluenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/influenced")
public class InfluenceController {
    private final InfluenceService service;
    private Logger logger = LoggerFactory.getLogger(InfluenceController.class);

    @Autowired
    public InfluenceController(InfluenceService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity get() {
        List<Influence> result = service.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

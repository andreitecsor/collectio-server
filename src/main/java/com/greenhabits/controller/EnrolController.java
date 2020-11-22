package com.greenhabits.controller;

import com.greenhabits.domain.relationship.Enrol;
import com.greenhabits.service.EnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r/enrol")
public class EnrolController {
    @Autowired
    private final EnrolService service;

    public EnrolController(EnrolService service) {
        this.service = service;
    }

    @PutMapping("/add/{idAppUser}-{idChallenge}")
    public ResponseEntity<Enrol> add(@PathVariable Long idAppUser, @PathVariable Long idChallenge) {
        Enrol result = service.create(idAppUser, idChallenge);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Enrol> getById(@PathVariable Long id) {
        Enrol result = service.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Enrol>> getAll() {
        List<Enrol> result = service.getAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Enrol> update(@RequestBody Enrol enrol) {
        Enrol result = service.update(enrol);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<Enrol> endChallenge(@PathVariable Long id) {
        Enrol result = service.endChallenge(id);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Enrol> deleteById(@PathVariable Long id) {
        Enrol result = service.delete(id);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

package com.greenhabits.controller;

import com.greenhabits.domain.node.Challenge;
import com.greenhabits.service.ChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("n/challenge")
public class ChallengeController {
    final private ChallengeService service;

    public ChallengeController(ChallengeService service) {
        this.service = service;
    }


    @PostMapping("/add")
    public ResponseEntity<Challenge> add(@RequestBody Challenge challengeToAdd) {
        Challenge newChallenge = service.create(challengeToAdd);
        if (newChallenge == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(newChallenge);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Challenge>> getAll() {
        List<Challenge> result = service.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Challenge> getById(@PathVariable Long id) {
        Challenge result = service.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Challenge> update(@RequestBody Challenge updatedChallenge) {
        Challenge result = service.update(updatedChallenge);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Challenge> deleteById(@PathVariable Long id) {
        Challenge result = service.delete(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

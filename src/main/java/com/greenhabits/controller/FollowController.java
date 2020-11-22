package com.greenhabits.controller;

import com.greenhabits.domain.relationship.Enrol;
import com.greenhabits.domain.relationship.Follow;
import com.greenhabits.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r/follow")
public class FollowController {
    @Autowired
    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }

    @PutMapping("/add/{idWhoFollows}-{idWhoIsFollowed}")
    public ResponseEntity<Follow> add(@PathVariable Long idWhoFollows, @PathVariable Long idWhoIsFollowed) {
        Follow result = service.create(idWhoFollows, idWhoIsFollowed);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Follow> getById(@PathVariable Long id) {
        Follow result = service.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Follow>> getAll() {
        List<Follow> result = service.getAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Follow> update(@RequestBody Follow follow) {
        Follow result = service.update(follow);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<Follow> endFollow(@PathVariable Long id) {
        Follow result = service.unfollow(id);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Follow> deleteById(@PathVariable Long id) {
        Follow result = service.delete(id);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

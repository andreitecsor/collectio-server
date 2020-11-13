package com.greenhabits.controller;

import com.greenhabits.domain.relationship.Follow;
import com.greenhabits.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("r/follow")
public class FollowController {
    @Autowired
    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Follow>> getAll() {
        List<Follow> result = service.getAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

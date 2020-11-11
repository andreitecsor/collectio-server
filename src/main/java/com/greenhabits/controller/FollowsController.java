package com.greenhabits.controller;

import com.greenhabits.domain.relationship.EnrolledIn;
import com.greenhabits.domain.relationship.Follows;
import com.greenhabits.service.EnrolledInService;
import com.greenhabits.service.FollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("follows")
public class FollowsController {
    @Autowired
    private final FollowsService service;

    public FollowsController(FollowsService service) {
        this.service = service;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Follows>> getAll() {
        List<Follows> result = service.getAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

package com.greenhabits.controller;

import com.greenhabits.domain.relationship.EnrolledIn;
import com.greenhabits.service.EnrolledInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("enrolled")
public class EnrolledInController {
    @Autowired
    private final EnrolledInService service;

    public EnrolledInController(EnrolledInService service) {
        this.service = service;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<EnrolledIn>> getAll(){
        List<EnrolledIn> result = service.getAll();
        if(result == null || result.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

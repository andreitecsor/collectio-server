package com.greenhabits.service;

import com.greenhabits.domain.relationship.EnrolledIn;
import com.greenhabits.repository.EnrolledInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledInService {
    @Autowired
    private EnrolledInRepository repository;

    public List<EnrolledIn> getAll() {
        return repository.findAll();
    }

    public void create(EnrolledIn enrolled) {
        repository.save(enrolled);
    }
}

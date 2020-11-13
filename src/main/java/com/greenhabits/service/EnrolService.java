package com.greenhabits.service;

import com.greenhabits.domain.relationship.Enrol;
import com.greenhabits.repository.EnrolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolService {
    @Autowired
    private EnrolRepository repository;

    public List<Enrol> getAll() {
        return repository.findAll();
    }

    public void create(Enrol enrolled) {
        repository.save(enrolled);
    }
}

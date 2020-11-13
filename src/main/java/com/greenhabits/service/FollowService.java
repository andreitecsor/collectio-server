package com.greenhabits.service;

import com.greenhabits.domain.relationship.Follow;
import com.greenhabits.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    private FollowRepository repository;

    public List<Follow> getAll() {
        return repository.findAll();
    }

    public void create(Follow follow) {
        repository.save(follow);
    }
}

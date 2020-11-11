package com.greenhabits.service;

import com.greenhabits.domain.node.GreenScout;
import com.greenhabits.domain.relationship.Follows;
import com.greenhabits.repository.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowsService {
    @Autowired
    private FollowsRepository repository;

    public List<Follows> getAll() {
        return repository.findAll();
    }

    public void create(Follows follow) {
        repository.save(follow);
    }
}

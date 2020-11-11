package com.greenhabits.service;

import com.greenhabits.domain.Challenge;
import com.greenhabits.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository repository;

    public List<Challenge> getAll(){
        return repository.findAll();
    }

    public void create(Challenge challenge){
        repository.save(challenge);
    }

}

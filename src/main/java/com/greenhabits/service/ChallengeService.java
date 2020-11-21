package com.greenhabits.service;

import com.greenhabits.domain.node.Challenge;
import com.greenhabits.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository repository;

    public Challenge create(Challenge challenge) {
        if (challenge == null) {
            return null;
        }
        return repository.save(challenge);
    }

    public List<Challenge> getAll() {
        return repository.findAll();
    }

    public Challenge getById(Long id) {
        Optional<Challenge> result = repository.findById(id);
        if (!result.isPresent()) {
            return null;
        }
        return result.get();
    }

    public Challenge update(Challenge challenge) {
        if (challenge == null || challenge.getId() == null) {
            return null;
        }
        Optional<Challenge> persistedChallenge = repository.findById(challenge.getId());
        if (!persistedChallenge.isPresent()) {
            return null;
        }

        Challenge challengeToUpdate = persistedChallenge.get();

        challengeToUpdate.setTitle(challenge.getTitle());
        return repository.save(challengeToUpdate);
    }

    public Challenge delete(Long id) {
        Optional<Challenge> persistedChallenge = repository.findById(id);
        if (!persistedChallenge.isPresent()) {
            return null;
        }
        Challenge deleted = persistedChallenge.get();
        repository.deleteById(id);
        return deleted;
    }

}

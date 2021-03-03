package com.greenhabits.service;

import com.greenhabits.domain.node.AppUser;
import com.greenhabits.domain.node.Challenge;
import com.greenhabits.domain.relationship.Enrol;
import com.greenhabits.repository.EnrolRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrolService {
    private final EnrolRepository repository;
    private final AppUserService appUserService;
    private final ChallengeService challengeService;

    public EnrolService(EnrolRepository repository, AppUserService appUserService, ChallengeService challengeService) {
        this.repository = repository;
        this.appUserService = appUserService;
        this.challengeService = challengeService;
    }

    public Enrol create(Long idAppUser, Long idChallenge) {
        AppUser appUser = appUserService.getById(idAppUser);
        Challenge challenge = challengeService.getById(idChallenge);

        if (appUser == null || challenge == null) {
            return null;
        }
        return repository.save(new Enrol(LocalDateTime.now(), appUser, challenge));
    }

    public List<Enrol> getAll() {
        return repository.findAll();
    }

    public Enrol getById(Long id) {
        Optional<Enrol> result = repository.findById(id);
        return result.orElse(null);
    }

    public Enrol update(Enrol enrol) {
        if (enrol == null || enrol.getId() == null) {
            return null;
        }
        Optional<Enrol> persistedEnrol = repository.findById(enrol.getId());
        if (!persistedEnrol.isPresent()) {
            return null;
        }

        AppUser appUser = appUserService.getById(enrol.getAppUser().getId());
        Challenge challenge = challengeService.getById(enrol.getChallenge().getId());
        if (appUser == null || challenge == null) {
            return null;
        }

        Enrol enrolToUpdate = persistedEnrol.get();

        enrolToUpdate.setStartedAt(enrol.getStartedAt());
        enrolToUpdate.setAppUser(appUser);
        enrolToUpdate.setChallenge(challenge);
        enrolToUpdate.setEndedAt(enrol.getEndedAt());

        return repository.save(enrolToUpdate);

    }

    public Enrol endChallenge(Long id) {
        Optional<Enrol> result = repository.findById(id);
        if (!result.isPresent()) {
            return null;
        }
        Enrol toEnd = result.get();
        toEnd.setEndedAt(LocalDateTime.now());
        return repository.save(toEnd);
    }

    public Enrol delete(Long id) {
        Optional<Enrol> persistedEnrol = repository.findById(id);
        if (!persistedEnrol.isPresent()) {
            return null;
        }
        Enrol deleted = persistedEnrol.get();
        repository.deleteById(id);
        return deleted;
    }
}

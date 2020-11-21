package com.greenhabits.service;

import com.greenhabits.domain.node.AppUser;
import com.greenhabits.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository repository;

    public AppUser create(AppUser appUser) {
        if (appUser == null) {
            return null;
        }
        return repository.save(appUser);
    }

    public List<AppUser> getAll() {
        return repository.findAll();
    }

    public AppUser getById(Long id) {
        Optional<AppUser> result = repository.findById(id);
        if (!result.isPresent()) {
            return null;
        }
        return result.get();
    }

    public AppUser update(AppUser user) {
        if (user == null || user.getId() == null) {
            return null;
        }
        Optional<AppUser> persistedUser = repository.findById(user.getId());
        if (!persistedUser.isPresent()) {
            return null;
        }

        AppUser userToUpdate = persistedUser.get();

        userToUpdate.setName(user.getName());
        userToUpdate.setCreatedAt(user.getCreatedAt());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setChallenges(user.getChallenges());
        return repository.save(userToUpdate);
    }

    public AppUser delete(Long id) {
        Optional<AppUser> persistedUser = repository.findById(id);
        if (!persistedUser.isPresent()) {
            return null;
        }
        AppUser deleted = persistedUser.get();
        repository.deleteById(id);
        return deleted;
    }


}

package com.greenhabits.service;

import com.greenhabits.domain.node.AppUser;
import com.greenhabits.domain.relationship.Follow;
import com.greenhabits.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {
    private final FollowRepository repository;
    private final AppUserService appUserService;

    public FollowService(FollowRepository repository, AppUserService appUserService) {
        this.repository = repository;
        this.appUserService = appUserService;
    }

    public Follow create(Long idWhoFollows, Long idWhoIsFollowed) {
        Date now = new Date();

        AppUser whoFollows = appUserService.getById(idWhoFollows);
        AppUser whoIsFollowed = appUserService.getById(idWhoIsFollowed);

        if (whoFollows == null || whoIsFollowed == null) {
            return null;
        }

        return repository.save(new Follow(now, whoFollows, whoIsFollowed));
    }

    public Follow getById(Long id) {
        Optional<Follow> result = repository.findById(id);
        return result.orElse(null);
    }

    public List<Follow> getAll() {
        return repository.findAll();
    }

    public Follow update(Follow follow) {
        if (follow == null || follow.getId() == null) {
            return null;
        }
        Optional<Follow> persistedFollow = repository.findById(follow.getId());
        if (!persistedFollow.isPresent()) {
            return null;
        }
        AppUser whoFollows = appUserService.getById(follow.getWhoFollows().getId());
        AppUser whoIsFollowed = appUserService.getById(follow.getWhoIsFollowed().getId());
        if (whoFollows == null || whoIsFollowed == null) {
            return null;
        }
        Follow followToUpdate = persistedFollow.get();
        followToUpdate.setStartedAt(follow.getStartedAt());
        followToUpdate.setWhoFollows(whoFollows);
        followToUpdate.setWhoIsFollowed(whoIsFollowed);
        followToUpdate.setEndedAt(follow.getEndedAt());
        return repository.save(followToUpdate);
    }

    public Follow unfollow(Long id) {
        Optional<Follow> result = repository.findById(id);
        if (!result.isPresent()) {
            return null;
        }
        Follow toUnfollow = result.get();
        Date now = new Date();
        toUnfollow.setEndedAt(now);
        return repository.save(toUnfollow);
    }

    public Follow delete(Long id) {
        Optional<Follow> persistedFollow = repository.findById(id);
        if (!persistedFollow.isPresent()) {
            return null;
        }
        Follow deleted = persistedFollow.get();
        repository.deleteById(id);
        return deleted;
    }

}

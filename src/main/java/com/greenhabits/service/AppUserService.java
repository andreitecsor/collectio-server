package com.greenhabits.service;

import com.greenhabits.domain.node.AppUser;
import com.greenhabits.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository repository;

    public List<AppUser> getAll(){
        return repository.findAll();
    }

    public void create(AppUser appUser){
        repository.save(appUser);
    }

}

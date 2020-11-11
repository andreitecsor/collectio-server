package com.greenhabits.service;

import com.greenhabits.domain.GreenScout;
import com.greenhabits.repository.GreenScoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreenScoutService {
    @Autowired
    private GreenScoutRepository repository;

    public List<GreenScout> getAll(){
        return repository.findAll();
    }

    public void create(GreenScout greenScout){
        repository.save(greenScout);
    }

}

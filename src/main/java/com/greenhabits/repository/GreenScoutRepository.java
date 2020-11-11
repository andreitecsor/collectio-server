package com.greenhabits.repository;

import com.greenhabits.domain.GreenScout;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreenScoutRepository extends Neo4jRepository<GreenScout, Long> {
    List<GreenScout> findAll();


}

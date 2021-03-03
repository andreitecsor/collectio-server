package com.greenhabits.repository;

import com.greenhabits.domain.relationship.Influence;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfluenceRepository extends Neo4jRepository<Influence, Long> {
    List<Influence> findAll();

    Optional<Influence> findById(Long id);
}

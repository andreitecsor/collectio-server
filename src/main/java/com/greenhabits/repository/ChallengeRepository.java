package com.greenhabits.repository;

import com.greenhabits.domain.node.Challenge;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends Neo4jRepository<Challenge, Long> {
    List<Challenge> findAll();

    Optional<Challenge> findById(Long id);
}

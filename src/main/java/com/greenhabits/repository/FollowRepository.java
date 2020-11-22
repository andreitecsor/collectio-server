package com.greenhabits.repository;


import com.greenhabits.domain.relationship.Follow;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends Neo4jRepository<Follow, Long> {
    List<Follow> findAll();

    Optional<Follow> findById(Long id);


}

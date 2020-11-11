package com.greenhabits.repository;


import com.greenhabits.domain.relationship.Follows;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface FollowsRepository extends Neo4jRepository<Follows, Long> {
    List<Follows> findAll();
}

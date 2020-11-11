package com.greenhabits.repository;

import com.greenhabits.domain.relationship.EnrolledIn;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolledInRepository extends Neo4jRepository<EnrolledIn,Long> {
    List<EnrolledIn> findAll();
}

package com.greenhabits.repository;

import com.greenhabits.domain.EnrolledIn;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolledInRepository extends Neo4jRepository<EnrolledIn,Long> {
}

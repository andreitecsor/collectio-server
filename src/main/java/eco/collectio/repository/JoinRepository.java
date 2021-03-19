package eco.collectio.repository;

import eco.collectio.domain.Join;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinRepository extends Neo4jRepository<Join, Long> {
    @Override
    List<Join> findAll();

    @Query("MATCH (user:User)-[relation:JOINED]->(challenge:Challenge) WHERE id(user) = $userId AND id(challenge) = $challengeId RETURN relation")
    Join findByNodesIds(Long userId, Long challengeId);


}

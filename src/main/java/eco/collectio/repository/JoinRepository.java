package eco.collectio.repository;

import eco.collectio.domain.Join;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoinRepository extends Neo4jRepository<Join, Long> {
    @Override
    List<Join> findAll();

    @Query("MATCH (user:User)-[relation:JOINED]->(challenge:Challenge) " +
            "WHERE user.uid = $userId AND id(challenge) = $challengeId " +
            "RETURN user,challenge,relation")
    Join findByNodesIds(String userId, Long challengeId);

    @Query("MATCH (user:User)-[relation:JOINED]->(challenge:Challenge) " +
            "WHERE user.uid = $userId AND NOT EXISTS(relation.endedAt) " +
            "RETURN user,challenge,relation")
    List<Join> findAllActives(String userId);

    @Query("MATCH (c:Challenge)-[:HAS]->(s:Stage) " +
            "MATCH (u:User)-[joined:JOINED]->(c) " +
            "WHERE id(s) = $stageId AND u.uid = $userId " +
            "RETURN c, u, joined")
    Optional<Join> findByUserAndStage(String userId, Long stageId);

}

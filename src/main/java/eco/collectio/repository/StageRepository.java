package eco.collectio.repository;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.Stage;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends Neo4jRepository<Stage, Long> {
    @Query("MATCH (challenge:Challenge)-[:HAS]->(stage:Stage) " +
            "WHERE id(challenge) = $challengeId " +
            "RETURN stage " +
            "ORDER BY stage.weeksCondition DESC")
    List<Stage> findAllByChallenge(Long challengeId);

    @Override
    Optional<Stage> findById(Long aLong);

    @Query("MATCH (challenge:Challenge)-[:HAS]->(stage:Stage) " +
            "WHERE id(stage) = $stageId " +
            "RETURN challenge " +
            "LIMIT 1")
    Challenge findChallenge(Long stageId);
}

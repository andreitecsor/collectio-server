package eco.collectio.repository;

import eco.collectio.domain.Reach;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReachRepository extends Neo4jRepository<Reach, Long> {

    @Query("MATCH (user:User)-[relation:REACHED]->(stage:Stage) " +
            "WHERE user.uid = $userId AND id(stage) = $stageId " +
            "RETURN user,stage,relation")
    Reach findByNodesIds(String userId, Long stageId);

    @Query("MATCH (user:User)-[relation:REACHED]->(stage:Stage)<-[:HAS]-(challenge:Challenge) " +
            "WHERE user.uid =$userId AND id(challenge)= $challengeId AND relation.show = true " +
            "SET relation.show = false " +
            "RETURN user,relation,stage")
    Reach hideActiveBadgeFromChallenge(String userId, Long challengeId);


    @Query("MATCH (u:User)-[reached:REACHED]->(s:Stage)" +
            "WHERE u.uid = $userId AND reached.show = true " +
            "RETURN reached,u,s")
    List<Reach> findAllShownBadges(String userId);
}

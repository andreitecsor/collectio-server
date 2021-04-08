package eco.collectio.repository;

import eco.collectio.domain.Follow;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends Neo4jRepository<Follow, Long> {
    @Query("MATCH (userWhoFollows:User)-[relation:FOLLOWS]->(userWhoIsFollowed:User) " +
            "WHERE id(userWhoFollows) = $idUserWhoFollows AND id(userWhoIsFollowed) = $idUserWhoIsFollowed " +
            "RETURN userWhoFollows,userWhoIsFollowed,relation")
    Follow findByNodesIds(Long idUserWhoFollows, Long idUserWhoIsFollowed);
}

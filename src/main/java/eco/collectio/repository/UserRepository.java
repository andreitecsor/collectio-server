package eco.collectio.repository;

import eco.collectio.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {
    @Override
    List<User> findAll();

    Optional<User> findByUid(String id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("MATCH (follows:User)-[relation:FOLLOWS]->(followed:User) " +
            "WHERE followed.uid = $userIdWhoIsFollowed AND NOT EXISTS(relation.lastTimeUnfollowed)" +
            "RETURN follows")
    List<User> findAllFollowers(String userIdWhoIsFollowed); //People who follow the specific user

    @Query("MATCH (follows:User)-[relation:FOLLOWS]->(followed:User) " +
            "WHERE follows.uid = $userIdWhoFollows AND NOT EXISTS(relation.lastTimeUnfollowed) " +
            "RETURN followed")
    List<User> findAllFollowings(String userIdWhoFollows); //People that the specific user is following


    @Query("MATCH(influencer:User)-[influenced:INFLUENCED]->(user:User) " +
            "WHERE influencer.uid = $userId " +
            "RETURN COUNT(user)")
    Integer findNoOfInfluencedByUserId(String userId);

    @Query("MATCH(influencer:User)-[influenced:INFLUENCED]->(user:User) " +
            "WHERE influencer.uid = $userId " +
            "RETURN SUM(influenced.timesInfluenced)")
    Integer findNoOfChallengesStartedBecauseOfUser(String userId);
}

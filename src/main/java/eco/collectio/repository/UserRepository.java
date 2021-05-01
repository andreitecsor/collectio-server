package eco.collectio.repository;

import eco.collectio.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    @Query("MATCH (follows:User)-[relation:FOLLOWS]->(followed:User) " +
            "WHERE id(followed) = $userIdWhoIsFollowed AND NOT EXISTS(relation.lastTimeUnfollowed)" +
            "RETURN follows")
    List<User> findAllFollowers(Long userIdWhoIsFollowed); //People who follow the specific user

    @Query("MATCH (follows:User)-[relation:FOLLOWS]->(followed:User) " +
            "WHERE id(follows) = $userIdWhoFollows AND NOT EXISTS(relation.lastTimeUnfollowed) " +
            "RETURN followed")
    List<User> findAllFollowings(Long userIdWhoFollows); //People that the specific user is following
}

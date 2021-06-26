package eco.collectio.repository;

import eco.collectio.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (post:Post)<-[:GENERATES]-(userWhoFollows:User)-[relation:FOLLOWS]->(userWhoIsFollowed:User) " +
            "WHERE userWhoFollows.uid = $idUserWhoFollows AND userWhoIsFollowed.uid = $idUserWhoIsFollowed " +
            "AND post.isVisible = true AND post.type = 'FOLLOW' " +
            "RETURN post")
    Optional<Post> findByUserIdAndFollowingId(String idUserWhoFollows, String idUserWhoIsFollowed);

    @Query(value = "MATCH (loggedUser:User)-[:FOLLOWS]->(followings:User)-[rel:GENERATES]->(posts:Post) " +
            "WHERE loggedUser.uid = $loggedUserId AND posts.isVisible = true " +
            "RETURN posts, followings, rel",
            countQuery = "MATCH (loggedUser:User)-[:FOLLOWS]->(followings:User)-[:GENERATES]->(posts:Post) " +
                    "WHERE loggedUser.uid = $loggedUserId AND posts.isVisible = true " +
                    "RETURN count(posts)")
    Page<Post> findNewsfeedPostsForLoggedUser(String loggedUserId, Pageable pageable);

    @Query(value = "MATCH (loggedUser:User)-[rel:GENERATES]->(posts:Post) " +
            "WHERE loggedUser.uid = $loggedUserId AND posts.isVisible = true " +
            "RETURN loggedUser, posts, rel",
            countQuery = "MATCH (loggedUser:User)-[:GENERATES]->(posts:Post) " +
                    "WHERE loggedUser.uid = $loggedUserId AND posts.isVisible = true " +
                    "RETURN count(posts)")
    Page<Post> findProfilePostForLoggedUser(String loggedUserId, PageRequest pageRequest);
}

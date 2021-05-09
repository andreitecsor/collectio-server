package eco.collectio.repository;

import eco.collectio.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (post:Post)<-[:GENERATES]-(userWhoFollows:User)-[relation:FOLLOWS]->(userWhoIsFollowed:User) " +
            "WHERE id(userWhoFollows) = $idUserWhoFollows AND id(userWhoIsFollowed) = $idUserWhoIsFollowed " +
            "AND post.isVisible = true " +
            "RETURN post")
    Optional<Post> findByUserIdAndFollowingId(Long idUserWhoFollows, Long idUserWhoIsFollowed);

    @Query(value = "MATCH (loggedUser:User)-[:FOLLOWS]->(followings:User)-[rel:GENERATES]->(posts:Post) " +
            "WHERE id(loggedUser) = $loggedUserId AND posts.isVisible = true " +
            "RETURN posts, followings, rel",
            countQuery = "MATCH (loggedUser:User)-[:FOLLOWS]->(followings:User)-[:GENERATES]->(posts:Post) " +
                    "WHERE id(loggedUser) = $loggedUserId AND posts.isVisible = true " +
                    "RETURN count(posts)")
    Page<Post> findPageForLoggedUSer(Long loggedUserId, Pageable pageable);
}

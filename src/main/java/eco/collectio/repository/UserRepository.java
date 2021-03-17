package eco.collectio.repository;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    @Override
    List<User> findAll();

    @Query("MATCH (users:User)-[:JOINED]->(challenge:Challenge)" +
            "WHERE id(c) = $id RETURN users")
    List<User> findAllByChallenge(Long id);
}

package eco.collectio.repository;

import eco.collectio.domain.Challenge;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends Neo4jRepository<Challenge, Long> {
    @Override
    List<Challenge> findAll();


}

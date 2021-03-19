package eco.collectio.repository;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends Neo4jRepository<Challenge, Long> {
    @Override
    List<Challenge> findAll();

    @Override
    Optional<Challenge> findById(Long id);
}

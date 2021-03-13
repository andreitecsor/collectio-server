package eco.collectio.repository;

import eco.collectio.domain.relationship.Enrol;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolRepository extends Neo4jRepository<Enrol, Long> {
    List<Enrol> findAll();

    Optional<Enrol> findById(Long id);
}

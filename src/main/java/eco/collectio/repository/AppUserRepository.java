package eco.collectio.repository;

import eco.collectio.domain.node.AppUser;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends Neo4jRepository<AppUser, Long> {
    List<AppUser> findAll();

    Optional<AppUser> findById(Long id);

}

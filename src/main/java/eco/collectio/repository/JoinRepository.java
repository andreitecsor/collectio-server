package eco.collectio.repository;

import eco.collectio.domain.Join;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinRepository extends Neo4jRepository<Join, Long> {
    @Override
    List<Join> findAll();

    //TODO:In momentul in care un user se inroleaza intr-o provocare
    Long findById();

}

package eco.collectio.repository;

import eco.collectio.domain.Influence;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluenceRepository extends Neo4jRepository<Influence, Long> {
    @Override
    List<Influence> findAll();

    @Query("MATCH (whoInfluenced:User)-[relation:INFLUENCED]->(whoIsInfluenced:User) " +
            " WHERE id(whoInfluenced) = $whoInfluencedId " +
            " AND id(whoIsInfluenced) = $whoIsInfluencedId " +
            " RETURN whoInfluenced,whoIsInfluenced,relation")
    Influence findByNodesIds(Long whoInfluencedId, Long whoIsInfluencedId);

}

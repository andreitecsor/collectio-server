package eco.collectio.repository;

import eco.collectio.domain.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

}

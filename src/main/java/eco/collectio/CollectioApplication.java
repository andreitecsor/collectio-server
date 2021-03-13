package eco.collectio;

import eco.collectio.repository.AppUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableNeo4jRepositories(basePackageClasses = AppUserRepository.class)
@SpringBootApplication
@EnableTransactionManagement
public class CollectioApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectioApplication.class, args);
    }
}

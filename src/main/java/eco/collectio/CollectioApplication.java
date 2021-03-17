package eco.collectio;

import eco.collectio.repository.ChallengeRepository;
import eco.collectio.repository.JoinRepository;
import eco.collectio.repository.UserRepository;
import eco.collectio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableNeo4jRepositories(basePackageClasses = {UserRepository.class, JoinRepository.class, ChallengeRepository.class})
@SpringBootApplication
@EnableTransactionManagement
public class CollectioApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(CollectioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Set<Challenge> joinedChallenges = userService.getAll().get(0).getJoinedChallenges();
//        System.out.println(joinedChallenges);
    }
}

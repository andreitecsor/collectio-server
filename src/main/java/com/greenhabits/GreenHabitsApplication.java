package com.greenhabits;

import com.greenhabits.domain.node.AppUser;
import com.greenhabits.domain.node.Challenge;
import com.greenhabits.domain.relationship.Enrol;
import com.greenhabits.domain.relationship.Follow;
import com.greenhabits.repository.AppUserRepository;
import com.greenhabits.service.ChallengeService;
import com.greenhabits.service.EnrolService;
import com.greenhabits.service.FollowService;
import com.greenhabits.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Instant;
import java.util.Date;

@EnableNeo4jRepositories(basePackageClasses = AppUserRepository.class)
@SpringBootApplication
@EnableTransactionManagement
public class GreenHabitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenHabitsApplication.class, args);
    }
}

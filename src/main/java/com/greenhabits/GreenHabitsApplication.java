package com.greenhabits;

import com.greenhabits.domain.Challenge;
import com.greenhabits.domain.EnrolledIn;
import com.greenhabits.domain.GreenScout;
import com.greenhabits.repository.GreenScoutRepository;
import com.greenhabits.service.ChallengeService;
import com.greenhabits.service.EnrolledInService;
import com.greenhabits.service.GreenScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableNeo4jRepositories(basePackageClasses = GreenScoutRepository.class)
@SpringBootApplication
@EnableTransactionManagement
public class GreenHabitsApplication implements CommandLineRunner {

    @Autowired
    private GreenScoutService greenScoutService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private EnrolledInService enrolledInService;

    public static void main(String[] args) {
        SpringApplication.run(GreenHabitsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        java.util.Date date = new java.util.Date();
        GreenScout user1 = new GreenScout("andrei","andrei@ie.ase.ro", date);
        GreenScout user2 = new GreenScout("andu","andrei@ie.ase.ro", date);
        Challenge c1 = new Challenge("bau1");
        Challenge c2 = new Challenge("bau2");
        greenScoutService.create(user2);

        EnrolledIn e = new EnrolledIn(date,user1,c1);
//        greenScoutService.create(user1);
        enrolledInService.create(e);

//        challengeService.create(c1);
        challengeService.create(c2);

        EnrolledIn e2 = new EnrolledIn(date,user2,c1);
//        greenScoutService.create(user1);
        enrolledInService.create(e2);

        System.out.println(challengeService.getAll());
        System.out.println(greenScoutService.getAll());
    }
}

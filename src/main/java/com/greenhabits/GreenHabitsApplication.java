package com.greenhabits;

import com.greenhabits.domain.node.Challenge;
import com.greenhabits.domain.relationship.EnrolledIn;
import com.greenhabits.domain.node.GreenScout;
import com.greenhabits.domain.relationship.Follows;
import com.greenhabits.repository.GreenScoutRepository;
import com.greenhabits.service.ChallengeService;
import com.greenhabits.service.EnrolledInService;
import com.greenhabits.service.FollowsService;
import com.greenhabits.service.GreenScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Instant;
import java.util.Date;

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

    @Autowired
    private FollowsService followsService;

    public static void main(String[] args) {
        SpringApplication.run(GreenHabitsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //Dummy data
//        java.util.Date date = new java.util.Date();
//        GreenScout user1 = new GreenScout("andrei","andrei@ie.ase.ro", Date.from(Instant.parse("2020-06-17T10:33:00Z")));
//        GreenScout user2 = new GreenScout("andu","andu@ie.ase.ro", Date.from(Instant.parse("2020-06-28T12:13:20Z")));
//        greenScoutService.create(user1);
//        greenScoutService.create(user2);
//
//        Follows follows = new Follows(Date.from(Instant.parse("2020-07-02T00:33:00Z")),user1,user2);
//        Follows followsBack = new Follows(Date.from(Instant.parse("2020-07-02T01:33:00Z")),user2,user1);
//        followsService.create(follows);
//        followsService.create(followsBack);
//
//        Challenge c1 = new Challenge("No car, health ++");
//        Challenge c2 = new Challenge("Less plastic, happier planet");
//        Challenge c3 = new Challenge("Recycle habit");
//        challengeService.create(c1);
//        challengeService.create(c2);
//        challengeService.create(c3);
//
//        EnrolledIn e1 = new EnrolledIn(Date.from(Instant.parse("2020-10-01T12:13:20Z")),user2,c1);
//        enrolledInService.create(e1);
//        EnrolledIn e2 = new EnrolledIn(date,user1,c1);
//        enrolledInService.create(e2);
//
//        EnrolledIn e3 = new EnrolledIn(Date.from(Instant.parse("2020-09-08T12:13:20Z")),user1,c3);
//        enrolledInService.create(e3);
//
//        EnrolledIn e4 = new EnrolledIn(date,user1,c2);
//        enrolledInService.create(e4);
    }
}

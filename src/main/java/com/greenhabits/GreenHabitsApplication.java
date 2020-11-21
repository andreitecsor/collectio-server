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
public class GreenHabitsApplication implements CommandLineRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private EnrolService enrolService;

    @Autowired
    private FollowService followService;

    public static void main(String[] args) {
        SpringApplication.run(GreenHabitsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //region Dummy data
//        java.util.Date date = new java.util.Date();
//        AppUser user1 = new AppUser("Andrei Tecsor","andrei@ie.ase.ro", Date.from(Instant.parse("2020-06-17T10:33:00Z")));
//        AppUser user2 = new AppUser("Andu_XD","andu@ie.ase.ro", Date.from(Instant.parse("2020-06-28T12:13:20Z")));
//        appUserService.create(user1);
//        appUserService.create(user2);
//
//        Follow follow = new Follow(Date.from(Instant.parse("2020-07-02T00:33:00Z")),user1,user2);
//        Follow followBack = new Follow(Date.from(Instant.parse("2020-07-02T01:33:00Z")),user2,user1);
//        followService.create(follow);
//        followService.create(followBack);
//
//        Challenge c1 = new Challenge("No car, health ++");
//        Challenge c2 = new Challenge("Less plastic, happier planet");
//        Challenge c3 = new Challenge("Recycle habit");
//        challengeService.create(c1);
//        challengeService.create(c2);
//        challengeService.create(c3);
//
//        Enrol e1 = new Enrol(Date.from(Instant.parse("2020-10-01T12:13:20Z")),user2,c1);
//        enrolService.create(e1);
//        Enrol e2 = new Enrol(date,user1,c1);
//        enrolService.create(e2);
//
//        Enrol e3 = new Enrol(Date.from(Instant.parse("2020-09-08T12:13:20Z")),user1,c3);
//        enrolService.create(e3);
//
//        Enrol e4 = new Enrol(date,user1,c2);
//        enrolService.create(e4);
        //endregion Dummy Data
    }
}

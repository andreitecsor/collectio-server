package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Relationship(type = "JOINED", direction = Relationship.OUTGOING)
    private Set<Challenge> joinedChallenges;

    public User(Long id, String name, Set<Challenge> joinedChallenges) {
        this.id = id;
        this.name = name;
        this.joinedChallenges = joinedChallenges;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Challenge> getJoinedChallenges() {
        return joinedChallenges;
    }
}

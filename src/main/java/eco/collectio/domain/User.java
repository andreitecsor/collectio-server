package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Set;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Relationship(type = "JOINED", direction = Relationship.OUTGOING)
    private Set<Challenge> activeChallenges;

    public User(Long id, String name, Set<Challenge> joinedChallenges) {
        this.id = id;
        this.name = name;
        //TODO: Parcurgere Set si doar in cazul in care relatia
        //      are endedAt
        this.activeChallenges = joinedChallenges;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Challenge> getActiveChallenges() {
        return activeChallenges;
    }
}

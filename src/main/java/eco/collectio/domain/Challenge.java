package eco.collectio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.util.Set;

@NodeEntity
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    @Relationship(type = "JOINED", direction = Relationship.INCOMING)
    private Set<User> usersJoined;

    public Challenge(Long id, String title, Set<User> usersJoined) {
        this.id = id;
        this.title = title;
        this.usersJoined = usersJoined;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        return title != null ? title.equals(challenge.title) : challenge.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @JsonIgnore
    public Set<User> getUsersJoined() {
        return usersJoined;
    }
}

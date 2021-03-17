package eco.collectio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "JOINED")
public class Join {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @StartNode
    private User user;

    @EndNode
    private Challenge challenge;

    public Join(Long id, LocalDateTime startedAt, User user, Challenge challenge) {
        this.id = id;
        this.startedAt = startedAt;
        this.user = user;
        this.challenge = challenge;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = LocalDateTime.now();
    }
}

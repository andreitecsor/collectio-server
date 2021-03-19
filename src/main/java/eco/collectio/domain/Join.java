package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "JOINED")
public class Join {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private LocalDateTime lastChecked;

    @StartNode
    private User user;

    @EndNode
    private Challenge challenge;

    public Join(LocalDateTime startedAt, User user, Challenge challenge) {
        this.startedAt = startedAt;
        this.lastChecked = this.startedAt;
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

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }

    public User getUser() {
        return user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setStartedAt() {
        this.startedAt = LocalDateTime.now();
        this.lastChecked = startedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Join{" +
                "id=" + id +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", lastChecked=" + lastChecked +
                ", challenge=" + challenge +
                '}';
    }
}

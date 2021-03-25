package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;

@RelationshipEntity(type = "JOINED")
public class Join {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate startedAt;

    private LocalDate endedAt;

    private LocalDate lastChecked;

    private Integer timesTried;

    @StartNode
    private User user;

    @EndNode
    private Challenge challenge;

    public Join(LocalDate startedAt, User user, Challenge challenge, Integer timesTried) {
        this.startedAt = startedAt;
        this.lastChecked = this.startedAt;
        this.user = user;
        this.challenge = challenge;
        this.timesTried = timesTried;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public LocalDate getEndedAt() {
        return endedAt;
    }

    public LocalDate getLastChecked() {
        return lastChecked;
    }

    public User getUser() {
        return user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Integer getTimesTried() {
        return timesTried;
    }

    public void restartChallenge() {
        if (this.endedAt != null) {
            this.startedAt = LocalDate.now();
            this.lastChecked = startedAt;
            this.endedAt = null;
            timesTried += 1;
        }
    }

    public void endChallenge() {
        this.endedAt = LocalDate.now();
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

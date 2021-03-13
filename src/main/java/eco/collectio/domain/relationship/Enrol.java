package eco.collectio.domain.relationship;

import eco.collectio.domain.node.AppUser;
import eco.collectio.domain.node.Challenge;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "ENROLLED_IN")
public class Enrol {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "startedAt")
    private LocalDateTime startedAt;

    @Property(value = "endedAt")
    private LocalDateTime endedAt;

    @StartNode
    private AppUser appUser;

    @EndNode
    private Challenge challenge;

    public Enrol() {
    }

    public Enrol(LocalDateTime startedAt, AppUser appUser, Challenge challenge) {
        this.startedAt = startedAt;
        this.endedAt = null;
        this.appUser = appUser;
        this.challenge = challenge;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enrol that = (Enrol) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startedAt != null ? !startedAt.equals(that.startedAt) : that.startedAt != null) return false;
        if (endedAt != null ? !endedAt.equals(that.endedAt) : that.endedAt != null) return false;
        if (appUser != null ? !appUser.equals(that.appUser) : that.appUser != null) return false;
        return challenge != null ? challenge.equals(that.challenge) : that.challenge == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        result = 31 * result + (endedAt != null ? endedAt.hashCode() : 0);
        result = 31 * result + (appUser != null ? appUser.hashCode() : 0);
        result = 31 * result + (challenge != null ? challenge.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EnrolledIn{" +
                "id=" + id +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", appUser=" + appUser +
                ", challenge=" + challenge +
                '}';
    }
}

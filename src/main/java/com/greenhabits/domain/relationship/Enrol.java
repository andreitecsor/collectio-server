package com.greenhabits.domain.relationship;

import com.greenhabits.domain.node.AppUser;
import com.greenhabits.domain.node.Challenge;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "ENROLLED_IN")
public class Enrol {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "started_at")
    private Date startedAt;

    @Property(value = "ended_at")
    private Date endedAt;

    @StartNode
    private AppUser appUser;

    @EndNode
    private Challenge challenge;

    public Enrol() {
    }

    public Enrol(Date startedAt, AppUser appUser, Challenge challenge) {
        this.startedAt = startedAt;
        this.endedAt = null;
        this.appUser = appUser;
        this.challenge = challenge;
    }

    public Long getId() {
        return id;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
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

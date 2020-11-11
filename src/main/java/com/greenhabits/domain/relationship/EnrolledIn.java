package com.greenhabits.domain.relationship;

import com.greenhabits.domain.node.Challenge;
import com.greenhabits.domain.node.GreenScout;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "ENROLLED_IN")
public class EnrolledIn {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "started_at")
    private Date startedAt;

    @Property(value = "ended_at")
    private Date endedAt;

    @StartNode
    private GreenScout greenScout;

    @EndNode
    private Challenge challenge;

    public EnrolledIn() {
    }

    public EnrolledIn(Date startedAt, GreenScout greenScout, Challenge challenge) {
        this.startedAt = startedAt;
        this.endedAt = null;
        this.greenScout = greenScout;
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

    public GreenScout getGreenScout() {
        return greenScout;
    }

    public void setGreenScout(GreenScout greenScout) {
        this.greenScout = greenScout;
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

        EnrolledIn that = (EnrolledIn) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startedAt != null ? !startedAt.equals(that.startedAt) : that.startedAt != null) return false;
        if (endedAt != null ? !endedAt.equals(that.endedAt) : that.endedAt != null) return false;
        if (greenScout != null ? !greenScout.equals(that.greenScout) : that.greenScout != null) return false;
        return challenge != null ? challenge.equals(that.challenge) : that.challenge == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        result = 31 * result + (endedAt != null ? endedAt.hashCode() : 0);
        result = 31 * result + (greenScout != null ? greenScout.hashCode() : 0);
        result = 31 * result + (challenge != null ? challenge.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EnrolledIn{" +
                "id=" + id +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", greenScout=" + greenScout +
                ", challenge=" + challenge +
                '}';
    }
}

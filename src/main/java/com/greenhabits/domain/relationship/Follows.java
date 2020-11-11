package com.greenhabits.domain.relationship;

import com.greenhabits.domain.node.GreenScout;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "FOLLOWS")
public class Follows {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "started_at")
    private Date startedAt;

    @Property(value = "ended_at")
    private Date endedAt;

    @StartNode
    private GreenScout follower;

    @EndNode
    private GreenScout following;

    public Follows() {
    }

    public Follows(Date startedAt, GreenScout follower, GreenScout following) {
        this.startedAt = startedAt;
        this.endedAt = null;
        this.follower = follower;
        this.following = following;
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

    public GreenScout getFollower() {
        return follower;
    }

    public void setFollower(GreenScout follower) {
        this.follower = follower;
    }

    public GreenScout getFollowing() {
        return following;
    }

    public void setFollowing(GreenScout following) {
        this.following = following;
    }
}

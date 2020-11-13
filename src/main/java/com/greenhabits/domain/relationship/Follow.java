package com.greenhabits.domain.relationship;

import com.greenhabits.domain.node.AppUser;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "FOLLOWS")
public class Follow {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "started_at")
    private Date startedAt;

    @Property(value = "ended_at")
    private Date endedAt;

    @StartNode
    private AppUser follower;

    @EndNode
    private AppUser following;

    public Follow() {
    }

    public Follow(Date startedAt, AppUser follower, AppUser following) {
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

    public AppUser getFollower() {
        return follower;
    }

    public void setFollower(AppUser follower) {
        this.follower = follower;
    }

    public AppUser getFollowing() {
        return following;
    }

    public void setFollowing(AppUser following) {
        this.following = following;
    }
}

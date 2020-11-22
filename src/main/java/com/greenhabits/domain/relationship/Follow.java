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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Follow follow = (Follow) o;

        if (id != null ? !id.equals(follow.id) : follow.id != null) return false;
        if (startedAt != null ? !startedAt.equals(follow.startedAt) : follow.startedAt != null) return false;
        if (endedAt != null ? !endedAt.equals(follow.endedAt) : follow.endedAt != null) return false;
        if (follower != null ? !follower.equals(follow.follower) : follow.follower != null) return false;
        return following != null ? following.equals(follow.following) : follow.following == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        result = 31 * result + (endedAt != null ? endedAt.hashCode() : 0);
        result = 31 * result + (follower != null ? follower.hashCode() : 0);
        result = 31 * result + (following != null ? following.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", follower=" + follower +
                ", following=" + following +
                '}';
    }
}

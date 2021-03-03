package com.greenhabits.domain.relationship;

import com.greenhabits.domain.node.AppUser;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RelationshipEntity(type = "FOLLOWS")
public class Follow {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "startedAt")
    private LocalDateTime startedAt;

    @Property(value = "endedAt")
    private LocalDateTime endedAt;

    @StartNode
    private AppUser whoFollows;

    @EndNode
    private AppUser whoIsFollowed;

    public Follow() {
    }

    public Follow(LocalDateTime startedAt, AppUser whoFollows, AppUser whoIsFollowed) {
        this.startedAt = startedAt;
        this.endedAt = null;
        this.whoFollows = whoFollows;
        this.whoIsFollowed = whoIsFollowed;
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

    public AppUser getWhoFollows() {
        return whoFollows;
    }

    public void setWhoFollows(AppUser whoFollows) {
        this.whoFollows = whoFollows;
    }

    public AppUser getWhoIsFollowed() {
        return whoIsFollowed;
    }

    public void setWhoIsFollowed(AppUser whoIsFollowed) {
        this.whoIsFollowed = whoIsFollowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Follow follow = (Follow) o;

        if (id != null ? !id.equals(follow.id) : follow.id != null) return false;
        if (startedAt != null ? !startedAt.equals(follow.startedAt) : follow.startedAt != null) return false;
        if (endedAt != null ? !endedAt.equals(follow.endedAt) : follow.endedAt != null) return false;
        if (whoFollows != null ? !whoFollows.equals(follow.whoFollows) : follow.whoFollows != null) return false;
        return whoIsFollowed != null ? whoIsFollowed.equals(follow.whoIsFollowed) : follow.whoIsFollowed == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        result = 31 * result + (endedAt != null ? endedAt.hashCode() : 0);
        result = 31 * result + (whoFollows != null ? whoFollows.hashCode() : 0);
        result = 31 * result + (whoIsFollowed != null ? whoIsFollowed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", follower=" + whoFollows +
                ", following=" + whoIsFollowed +
                '}';
    }
}

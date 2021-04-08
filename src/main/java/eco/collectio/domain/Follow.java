package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "FOLLOWS")
public class Follow {
    @GeneratedValue
    @Id
    private Long id;

    @StartNode
    private User userWhoFollows;

    @EndNode
    private User userWhoIsFollowed;

    private int timesFollowed;

    private LocalDateTime firstTimeFollowed;

    private LocalDateTime lastTimeFollowed;

    private LocalDateTime lastTimeUnfollowed;

    public Follow(User userWhoFollows, User userWhoIsFollowed) {
        this.userWhoFollows = userWhoFollows;
        this.userWhoIsFollowed = userWhoIsFollowed;
        this.firstTimeFollowed = LocalDateTime.now();
        this.lastTimeFollowed = this.firstTimeFollowed;
        this.lastTimeUnfollowed = null;
        this.timesFollowed = 1;
    }

    public Long getId() {
        return id;
    }

    public User getUserWhoFollows() {
        return userWhoFollows;
    }

    public User getUserWhoIsFollowed() {
        return userWhoIsFollowed;
    }

    public int getTimesFollowed() {
        return timesFollowed;
    }

    public LocalDateTime getFirstTimeFollowed() {
        return firstTimeFollowed;
    }

    public LocalDateTime getLastTimeFollowed() {
        return lastTimeFollowed;
    }

    public LocalDateTime getLastTimeUnfollowed() {
        return lastTimeUnfollowed;
    }

    public void unfollow() {
        this.lastTimeUnfollowed = LocalDateTime.now();
    }

    public void followAgain() {
        this.lastTimeFollowed = LocalDateTime.now();
        this.lastTimeUnfollowed = null;
        this.timesFollowed++;
    }
}

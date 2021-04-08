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

    private LocalDateTime firstTime;

    private LocalDateTime lastTime;

    private LocalDateTime lastEnded;

    public Follow(User userWhoFollows, User userWhoIsFollowed) {
        this.userWhoFollows = userWhoFollows;
        this.userWhoIsFollowed = userWhoIsFollowed;
        this.firstTime = LocalDateTime.now();
        this.lastTime = this.firstTime;
        this.lastEnded = null;
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

    public LocalDateTime getFirstTime() {
        return firstTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public LocalDateTime getLastEnded() {
        return lastEnded;
    }

    public void unfollow() {
        this.lastEnded = LocalDateTime.now();
    }

    public void followAgain() {
        this.lastTime = LocalDateTime.now();
        this.lastEnded = null;
        this.timesFollowed++;
    }
}

package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;

@RelationshipEntity(type = "REACHED")
public class Reach {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate completedAt;

    private Boolean show;

    private Integer timesReached;

    @StartNode
    private User user;

    @EndNode
    private Stage stage;

    public Reach(User user, Stage stage) {
        this.user = user;
        this.stage = stage;
        this.completedAt = LocalDate.now();
        this.show = true;
        this.timesReached = 1;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public Boolean getShow() {
        return show;
    }

    public Integer getTimesReached() {
        return timesReached;
    }

    public User getUser() {
        return user;
    }

    public Stage getStage() {
        return stage;
    }

    public void reachievedStage() {
        this.show = true;
        this.timesReached++;
    }
}

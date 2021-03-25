package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;

@RelationshipEntity(type = "INFLUENCED")
public class Influence {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User whoInfluenced;

    @EndNode
    private User whoIsInfluenced;

    private Integer timesInfluenced;

    private LocalDate lastTime;

    public Influence(User whoInfluenced, User whoIsInfluenced, Integer timesInfluenced, LocalDate when) {
        this.whoInfluenced = whoInfluenced;
        this.whoIsInfluenced = whoIsInfluenced;
        this.timesInfluenced = timesInfluenced;
        this.lastTime = when;
    }

    public Long getId() {
        return id;
    }

    public User getWhoInfluenced() {
        return whoInfluenced;
    }

    public User getWhoIsInfluenced() {
        return whoIsInfluenced;
    }

    public Integer getTimesInfluenced() {
        return timesInfluenced;
    }

    public LocalDate getLastTime() {
        return lastTime;
    }

    public void increaseInfluence() {
        this.timesInfluenced += 1;
        this.lastTime = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Influence{" +
                "id=" + id +
                ", whoInfluenced=" + whoInfluenced +
                ", whoIsInfluenced=" + whoIsInfluenced +
                ", timesInfluenced=" + timesInfluenced +
                ", lastTime=" + lastTime +
                '}';
    }
}

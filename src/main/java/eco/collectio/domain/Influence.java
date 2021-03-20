package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "INFLUENCED")
public class Influence {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User whoInfluenced;

    @EndNode
    private User whoIsInfluenced;

    private Long challengeId;

    private LocalDateTime when;

    public Influence(User whoInfluenced, User whoIsInfluenced, Long challenge, LocalDateTime when) {
        this.whoInfluenced = whoInfluenced;
        this.whoIsInfluenced = whoIsInfluenced;
        this.challengeId = challenge;
        this.when = when;
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

    public Long getChallengeId() {
        return challengeId;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    @Override
    public String toString() {
        return "Influence{" +
                "id=" + id +
                ", whoInfluenced=" + whoInfluenced +
                ", whoIsInfluenced=" + whoIsInfluenced +
                ", challengeId=" + challengeId +
                ", when=" + when +
                '}';
    }
}

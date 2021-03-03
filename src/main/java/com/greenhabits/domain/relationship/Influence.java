package com.greenhabits.domain.relationship;

import com.greenhabits.domain.node.AppUser;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;

@RelationshipEntity(type = "INFLUENCED")
public class Influence {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "when")
    private LocalDateTime when;

    @Property(value = "challengeId")
    private Long challengeId;

    @StartNode
    private AppUser whoInfluenced;

    @EndNode
    private AppUser whoIsInfluenced;

    public Influence(LocalDateTime when, Long challengeId, AppUser whoInfluenced, AppUser whoIsInfluenced) {
        this.when = when;
        this.challengeId = challengeId;
        this.whoInfluenced = whoInfluenced;
        this.whoIsInfluenced = whoIsInfluenced;
    }

    public Influence() {
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public AppUser getWhoInfluenced() {
        return whoInfluenced;
    }

    public void setWhoInfluenced(AppUser whoInfluenced) {
        this.whoInfluenced = whoInfluenced;
    }

    public AppUser getWhoIsInfluenced() {
        return whoIsInfluenced;
    }

    public void setWhoIsInfluenced(AppUser whoIsInfluenced) {
        this.whoIsInfluenced = whoIsInfluenced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Influence influence = (Influence) o;

        if (id != null ? !id.equals(influence.id) : influence.id != null) return false;
        if (when != null ? !when.equals(influence.when) : influence.when != null) return false;
        if (challengeId != null ? !challengeId.equals(influence.challengeId) : influence.challengeId != null)
            return false;
        if (whoInfluenced != null ? !whoInfluenced.equals(influence.whoInfluenced) : influence.whoInfluenced != null)
            return false;
        return whoIsInfluenced != null ? whoIsInfluenced.equals(influence.whoIsInfluenced) : influence.whoIsInfluenced == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (when != null ? when.hashCode() : 0);
        result = 31 * result + (challengeId != null ? challengeId.hashCode() : 0);
        result = 31 * result + (whoInfluenced != null ? whoInfluenced.hashCode() : 0);
        result = 31 * result + (whoIsInfluenced != null ? whoIsInfluenced.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Influence{" +
                "id=" + id +
                ", when=" + when +
                ", challengeId=" + challengeId +
                ", whoInfluenced=" + whoInfluenced +
                ", whoIsInfluenced=" + whoIsInfluenced +
                '}';
    }
}

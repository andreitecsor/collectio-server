package com.greenhabits.domain.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Challenge")
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "title")
    private String title;

//    @Relationship(type = "ENROLLED_IN", direction = Relationship.INCOMING)
//    @Relationship(type = "ENROLLED_IN")
    private Set<GreenScout> greenScouts;

    public Challenge() {
    }

    public Challenge(String title) {
        this.title = title;
        this.greenScouts = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public Set<GreenScout> getGreenScouts() {
        return greenScouts;
    }

    public void setGreenScouts(Set<GreenScout> greenScouts) {
        this.greenScouts = greenScouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        if (id != null ? !id.equals(challenge.id) : challenge.id != null) return false;
        if (title != null ? !title.equals(challenge.title) : challenge.title != null) return false;
        return greenScouts != null ? greenScouts.equals(challenge.greenScouts) : challenge.greenScouts == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (greenScouts != null ? greenScouts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

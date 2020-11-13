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
    private Set<AppUser> appUsers;

    public Challenge() {
    }

    public Challenge(String title) {
        this.title = title;
        this.appUsers = new HashSet<>();
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
    public Set<AppUser> getGreenScouts() {
        return appUsers;
    }

    public void setGreenScouts(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        if (id != null ? !id.equals(challenge.id) : challenge.id != null) return false;
        if (title != null ? !title.equals(challenge.title) : challenge.title != null) return false;
        return appUsers != null ? appUsers.equals(challenge.appUsers) : challenge.appUsers == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (appUsers != null ? appUsers.hashCode() : 0);
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

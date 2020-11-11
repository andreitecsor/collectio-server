package com.greenhabits.domain;

import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@NodeEntity
public class GreenScout {
    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "name")
    private String name;

    @Property(value = "email")
    private String email;

    @Property(value = "created_at")
    private LocalDate createdAt;

    public GreenScout() {
    }

    public GreenScout(String name, String email, LocalDate createdAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    @Relationship(type = "ENROLLED_IN",direction = Relationship.OUTGOING)
    private Set<Challenge> challenges;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GreenScout that = (GreenScout) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GreenScout{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

package eco.collectio.domain;

import org.neo4j.ogm.annotation.*;

import java.util.List;

@NodeEntity
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    @Relationship(type = "HAS", direction = Relationship.OUTGOING)
    private List<Stage> stages;

    public Challenge(Long id, String title, List<Stage> stages) {
        this.id = id;
        this.title = title;
        this.stages = stages;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Stage> getStages() {
        return stages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        return title != null ? title.equals(challenge.title) : challenge.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

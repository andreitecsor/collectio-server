package eco.collectio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Challenge {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    private String description;

    private String logoUrl;

    @Relationship(type = "HAS", direction = Relationship.OUTGOING)
    private List<Stage> stages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        return title.equals(challenge.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}

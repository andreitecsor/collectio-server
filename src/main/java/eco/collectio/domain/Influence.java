package eco.collectio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Influence(User whoInfluenced, User whoIsInfluenced) {
        this.whoInfluenced = whoInfluenced;
        this.whoIsInfluenced = whoIsInfluenced;
        this.timesInfluenced = 1;
        this.lastTime = LocalDate.now();
    }

    public void increaseInfluence() {
        this.timesInfluenced += 1;
        this.lastTime = LocalDate.now();
    }
}

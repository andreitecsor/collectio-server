package eco.collectio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public void reachievedStage() {
        this.show = true;
        this.timesReached++;
    }
}

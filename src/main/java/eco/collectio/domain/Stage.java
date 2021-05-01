package eco.collectio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Stage {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private int weeksCondition;

    //private String badgeUrl;

    public int getWeeksCondition() {
        return weeksCondition;
    }
}

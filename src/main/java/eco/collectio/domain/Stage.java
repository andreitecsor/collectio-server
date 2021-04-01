package eco.collectio.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Stage {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private  int weeksCondition;

    //private String badgeUrl;


    public Stage(String description, int weeksCondition) {
        this.description = description;
        this.weeksCondition = weeksCondition;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getWeeksCondition() {
        return weeksCondition;
    }
}

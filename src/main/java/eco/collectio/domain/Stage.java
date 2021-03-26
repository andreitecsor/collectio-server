package eco.collectio.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NodeEntity
public class Stage {
    @Id
    @GeneratedValue
    private Long id;

    private String rank;

    private int weeksCondition;

    private LocalDate localDate;

//    private String description;
//    private String badgeUrl;


    public Stage(String rank, int weeksCondition) {
        this.rank = rank;
        this.weeksCondition = weeksCondition;
    }

    public Long getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public int getWeeksCondition() {
        return weeksCondition;
    }
}

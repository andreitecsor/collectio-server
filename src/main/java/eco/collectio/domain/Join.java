package eco.collectio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type = "JOINED")
public class Join {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate startedAt;

    private LocalDate endedAt;

    private LocalDate lastChecked;

    private Integer timesTried;

    private Integer bestRecord; // number of weeks

    @StartNode
    private User user;

    @EndNode
    private Challenge challenge;

    public Join(User user, Challenge challenge) {
        this.startedAt = LocalDate.now();
        this.lastChecked = this.startedAt;
        this.user = user;
        this.challenge = challenge;
        this.timesTried = 1;
        this.bestRecord = 0;
    }

    public void restartChallenge() {
        if (this.endedAt != null) {
            this.startedAt = LocalDate.now();
            this.lastChecked = startedAt;
            this.endedAt = null;
            timesTried += 1;
        }
    }

    public void endChallenge() {
        this.endedAt = LocalDate.now();
        int weeks = (int) ChronoUnit.WEEKS.between(this.startedAt, this.endedAt);
        if (weeks > this.bestRecord) {
            this.bestRecord = weeks;
        }
    }

    public void checkChallenge() {
        this.lastChecked = this.lastChecked.plusDays(7);
        int weeks = (int) ChronoUnit.WEEKS.between(this.startedAt, this.lastChecked);
        if (weeks > this.bestRecord) {
            this.bestRecord = weeks;
        }
    }


}

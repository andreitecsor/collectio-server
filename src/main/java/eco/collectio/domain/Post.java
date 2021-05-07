package eco.collectio.domain;

import eco.collectio.exception.InvalidPostException;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;

@NodeEntity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "GENERATES", direction = Relationship.INCOMING)
    private User user;

    private PostType type;
    private LocalDateTime createdAt;


    private Long challengeId;
    private String challengeTitle;
    //private String challengeLogoLink;

    private Long stageId;
    private Integer stageWeeksCondition;
//    private String badgeLink;

    private Long followingId;
    private String followingName;

    private Post(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public Long getStageId() {
        return stageId;
    }

    public Integer getStageWeeksCondition() {
        return stageWeeksCondition;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public String getFollowingName() {
        return followingName;
    }

    public static class PostBuilder {
        private Post post;

        public PostBuilder(PostType type, User user) {
            this.post = new Post(user);
            this.post.type = type;
        }

        public PostBuilder setChallenge(Challenge challenge) {
            if ((this.post.type == PostType.CHALLENGE || this.post.type == PostType.AWARD)
                    && challenge != null) {
                this.post.challengeId = challenge.getId();
                this.post.challengeTitle = challenge.getTitle();
                return this;
            }
            return this;
        }

        public PostBuilder setStage(Stage stage) {
            if (post.type == PostType.AWARD && stage != null) {
                post.stageId = stage.getId();
                post.stageWeeksCondition = stage.getWeeksCondition();
                return this;
            }
            return this;
        }

        public PostBuilder setFollowing(User following) {
            if (post.type == PostType.FOLLOW && following != null) {
                post.followingId = following.getId();
                post.followingName = following.getDisplayName();
                return this;
            }
            return this;
        }

        public Post build() throws InvalidPostException {
            switch (post.type) {
                case AWARD: {
                    if (this.post.challengeId == null || this.post.stageId == null) {
                        throw new InvalidPostException("An AWARD type post must have a stage that was " +
                                "completed and a challenge");
                    }
                    return this.post;
                }
                case CHALLENGE: {
                    if (this.post.challengeId == null) {
                        throw new InvalidPostException("A CHALLENGE type post must have a started challenge");
                    }
                    return this.post;
                }
                case FOLLOW: {
                    if (this.post.followingId == null) {
                        throw new InvalidPostException("A FOLLOW type post must have a person who started following");
                    }
                    return this.post;
                }
                default:
                    throw new InvalidPostException("There is a problem with the PostType");
            }
        }

    }
}

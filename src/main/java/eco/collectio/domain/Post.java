package eco.collectio.domain;

import eco.collectio.exception.InvalidPostException;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;

@Data
@NodeEntity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "GENERATES", direction = Relationship.INCOMING)
    private User user;

    private PostType type;
    private LocalDateTime createdAt;
    private Boolean isVisible;

    private Long challengeId;
    private Long stageId;
    private String followingId;

    private Post(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.isVisible = true;
    }

    public void makeUnavailable() {
        this.isVisible = false;
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
                return this;
            }
            return this;
        }

        public PostBuilder setStage(Stage stage) {
            if (post.type == PostType.AWARD && stage != null) {
                post.stageId = stage.getId();
                return this;
            }
            return this;
        }

        public PostBuilder setFollowing(User following) {
            if (post.type == PostType.FOLLOW && following != null) {
                post.followingId = following.getUid();
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

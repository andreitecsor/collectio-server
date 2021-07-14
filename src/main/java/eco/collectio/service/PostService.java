package eco.collectio.service;

import eco.collectio.domain.*;
import eco.collectio.dto.PostDetails;
import eco.collectio.exception.InvalidPostException;
import eco.collectio.repository.PostRepository;
import eco.collectio.utils.PostScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ChallengeService challengeService;
    private final StageService stageService;

    @Autowired
    public PostService(PostRepository postRepository,
                       UserService userService, ChallengeService challengeService, StageService stageService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.challengeService = challengeService;
        this.stageService = stageService;
    }

    public List<PostDetails> getNewsfeedPosts(String loggedUserId, int page, int size, PostScope postScope) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("posts.createdAt").descending());
        Page<Post> posts = null;
        if (postScope == PostScope.NEWSFEED) {
            posts = postRepository.findNewsfeedPostsForLoggedUser(loggedUserId, pageRequest);
        } else {
            posts = postRepository.findProfilePostForLoggedUser(loggedUserId, pageRequest);
        }
        List<PostDetails> newsfeedPosts = new ArrayList<>();
        for (Post post : posts.getContent()) {
            PostDetails postDetails = null;
            switch (post.getType()) {
                case FOLLOW:
                    Optional<User> following = userService.getById(post.getFollowingId());
                    if (following.isPresent()) {
                        postDetails = new PostDetails(post.getId(), PostType.FOLLOW, post.getUser(), post.getCreatedAt(),
                                null, null, following.get());
                    }
                    break;
                case CHALLENGE:
                    Optional<Challenge> challenge = challengeService.getById(post.getChallengeId());
                    if (challenge.isPresent()) {
                        postDetails = new PostDetails(post.getId(), PostType.CHALLENGE, post.getUser(), post.getCreatedAt(),
                                challenge.get(), null, null);
                    }
                    break;
                case AWARD:
                    Optional<Challenge> challengeAward = challengeService.getById(post.getChallengeId());
                    Optional<Stage> stageAward = stageService.getById(post.getStageId());
                    if (challengeAward.isPresent() && stageAward.isPresent()) {
                        postDetails = new PostDetails(post.getId(), PostType.AWARD, post.getUser(), post.getCreatedAt(),
                                challengeAward.get(), stageAward.get(), null);
                    }
                    break;
                default:
                    throw new InvalidPostException("There is a problem with the PostType");
            }
            if (postDetails != null) {
                newsfeedPosts.add(postDetails);
            }
        }
        return newsfeedPosts;
    }

    public Optional<Post> getPostByFollowerIdAndFollowingId(String idUserWhoFollows, String idUserWhoIsFollowed) {
        return postRepository.findByUserIdAndFollowingId(idUserWhoFollows, idUserWhoIsFollowed);
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

}

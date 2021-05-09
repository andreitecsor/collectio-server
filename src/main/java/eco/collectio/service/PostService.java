package eco.collectio.service;

import eco.collectio.domain.Post;
import eco.collectio.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPostByFollowerIdAndFollowingId(Long idUserWhoFollows, Long idUserWhoIsFollowed) {
        return postRepository.findByUserIdAndFollowingId(idUserWhoFollows,idUserWhoIsFollowed);
    }

    public Post upsert(Post post) {
        return postRepository.save(post);
    }
}

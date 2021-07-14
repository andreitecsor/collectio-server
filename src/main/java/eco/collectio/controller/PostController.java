package eco.collectio.controller;

import eco.collectio.dto.PostDetails;
import eco.collectio.service.PostService;
import eco.collectio.utils.PostScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/newsfeed/public")
    public ResponseEntity<List<PostDetails>> getPublicNewsfeedPage(@RequestParam(value = "user") String loggedUserId,
                                                                   @RequestParam int page) {
        List<PostDetails> result = postService.getNewsfeedPosts(loggedUserId, page, 10,
                PostScope.NEWSFEED);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/newsfeed/personal")
    public ResponseEntity<List<PostDetails>> getPersonalNewsfeedPage(@RequestParam(value = "user") String loggedUserId,
                                                                     @RequestParam int page) {
        List<PostDetails> result = postService.getNewsfeedPosts(loggedUserId, page, 10,
                PostScope.PERSONAL);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

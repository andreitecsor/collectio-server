package eco.collectio.controller;

import eco.collectio.domain.dto.PostDetails;
import eco.collectio.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{loggedUserId}/{page}")
    public ResponseEntity getNewsfeedPage(@PathVariable Long loggedUserId, @PathVariable int page) {
        List<PostDetails> result = postService.getNewsfeedPosts(loggedUserId, page, 10);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

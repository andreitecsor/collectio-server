package eco.collectio.controller;

import eco.collectio.domain.Follow;
import eco.collectio.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/follows")
public class FollowController {
    private final FollowService followService;
    private final Logger LOGGER = LoggerFactory.getLogger(FollowController.class);

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PutMapping("/{idUserWhoFollows}->{idUserWhoIsFollowed}")
    public ResponseEntity<Follow> follow(@PathVariable String idUserWhoFollows,
                                         @PathVariable String idUserWhoIsFollowed) {
        Follow result = followService.follow(idUserWhoFollows, idUserWhoIsFollowed);
        if (result == null || result.getLastTimeUnfollowed() != null) {
            LOGGER.error(" FOLLOWS relationship requested with idUserWhoFollows= " + idUserWhoFollows +
                    ", idUserWhoIsFollowed= " + idUserWhoIsFollowed +
                    " it's still active. ");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/end/{idUserWhoFollows}->{idUserWhoIsFollowed}")
    public ResponseEntity<Follow> unfollow(@PathVariable String idUserWhoFollows,
                                           @PathVariable String idUserWhoIsFollowed) {
        Follow result = followService.unfollow(idUserWhoFollows, idUserWhoIsFollowed);
        if (result == null) {
            LOGGER.error(" FOLLOWS relationship requested with idUserWhoFollows=" + idUserWhoFollows +
                    ", idUserWhoIsFollowed=" + idUserWhoIsFollowed +
                    " does not exist or it's already ended. ");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}

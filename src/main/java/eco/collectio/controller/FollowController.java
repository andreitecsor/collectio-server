package eco.collectio.controller;

import eco.collectio.domain.Follow;
import eco.collectio.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("follow")
public class FollowController {
    private final FollowService followService;
    private Logger logger = LoggerFactory.getLogger(FollowController.class);

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PutMapping("/{idUserWhoFollows}-{idUserWhoIsFollowed}")
    public ResponseEntity add(@PathVariable Long idUserWhoFollows, @PathVariable Long idUserWhoIsFollowed) {
        Follow result = followService.upsert(idUserWhoFollows, idUserWhoIsFollowed);
        if (result == null || result.getLastTimeUnfollowed() != null) {
            logger.error(" FOLLOWS relationship requested with idUserWhoFollows= " + idUserWhoFollows +
                    ", idUserWhoIsFollowed= " + idUserWhoIsFollowed +
                    " does not exist or it's still active. ");
            return ResponseEntity.badRequest().body("Invalid path variables");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/end/{idUserWhoFollows}-{idUserWhoIsFollowed}")
    public ResponseEntity unfollow(@PathVariable Long idUserWhoFollows, @PathVariable Long idUserWhoIsFollowed) {
        Follow result = followService.unfollow(idUserWhoFollows, idUserWhoIsFollowed);
        if (result == null) {
            logger.error(" FOLLOWS relationship requested with idUserWhoFollows= " + idUserWhoFollows +
                    ", idUserWhoIsFollowed= " + idUserWhoIsFollowed +
                    " does not exist or it's already ended. ");
            return ResponseEntity.badRequest().body("Invalid path variables");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}

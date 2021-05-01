package eco.collectio.controller;

import eco.collectio.domain.User;
import eco.collectio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity get() {
        List<User> result = userService.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{email}")
    public ResponseEntity getByEmail(@PathVariable String email) {
        Optional<User> result = userService.getByEmail(email);
        if (!result.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity getFollowers(@PathVariable Long id) {
        List<User> result = userService.getAllFollowers(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/followings/{id}")
    public ResponseEntity getFollowings(@PathVariable Long id) {
        List<User> result = userService.getAllFollowings(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody User user) {
        User result = userService.create(user);
        if (result == null) {
            logger.error("user attributes does not match the default or have null values");
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

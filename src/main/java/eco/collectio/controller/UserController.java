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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> result = userService.getAll();
        if (result == null) {
            LOGGER.info("No users found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        Optional<User> result = userService.getById(id);
        return result.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/find")
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        Optional<User> result = userService.getByEmail(email);
        return result.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable String id) {
        List<User> result = userService.getAllFollowers(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}/followings")
    public ResponseEntity<List<User>> getFollowings(@PathVariable String id) {
        List<User> result = userService.getAllFollowings(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<User> add(@RequestBody User user) {
        User result = userService.create(user);
        if (result == null) {
            LOGGER.error("user attributes does not match the default or have null values");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        User result = userService.update(id, user);
        if (result == null) {
            LOGGER.error("user attributes does not match the default or already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}

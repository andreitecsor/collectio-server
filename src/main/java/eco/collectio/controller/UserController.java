package eco.collectio.controller;

import eco.collectio.domain.User;
import eco.collectio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

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

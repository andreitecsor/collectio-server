package eco.collectio.controller;

import eco.collectio.domain.User;
import eco.collectio.service.JoinService;
import eco.collectio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, JoinService joinService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        List<User> result = userService.get();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        User result = userService.create(user);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

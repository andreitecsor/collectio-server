package eco.collectio.controller;

import eco.collectio.domain.node.AppUser;
import eco.collectio.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("n/user")
public class AppUserController {
    final private AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> add(@RequestBody AppUser userToAdd) {
        AppUser newUser = service.create(userToAdd);
        if (newUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<AppUser>> getAll() {
        List<AppUser> result = service.getAll();
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AppUser> getById(@PathVariable Long id) {
        AppUser result = service.getById(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<AppUser> update(@RequestBody AppUser updatedUser) {
        AppUser result = service.update(updatedUser);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AppUser> deleteById(@PathVariable Long id) {
        AppUser result = service.delete(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}

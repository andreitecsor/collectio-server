package eco.collectio.service;

import eco.collectio.domain.User;
import eco.collectio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> get() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        //TODO:Should check if the user already exists based on email.
        if (user == null || user.getName() == null) {
            return null;
        }
        return repository.save(user);
    }
}

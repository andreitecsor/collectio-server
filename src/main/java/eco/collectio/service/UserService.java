package eco.collectio.service;

import eco.collectio.domain.User;
import eco.collectio.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> get() {
        return repository.findAll();
    }

    public List<User> getAllFollowers(Long id) {
        return repository.findAllFollowers(id);
    }

    public List<User> getAllFollowings(Long id) {
        return repository.findAllFollowings(id);
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User create(User user) {
        if (user == null || user.getEmail() == null || user.getDisplayName() == null) {
            return null;
        }
        if (getByEmail(user.getEmail()).isPresent()) {
            logger.error("Already existing email address");
            return null;
        }
        return repository.save(user);
    }
}

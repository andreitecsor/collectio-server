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

    /**
     * @return all users from database
     */
    public List<User> get() {
        return repository.findAll();
    }

    /**
     * @param id user's unique id
     * @return specific user from database via id
     */
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    /**
     * @param user's properties without id
     * @return the new user added or null if: user is null or it's data types are incorrect
     */
    public User create(User user) {
        //TODO:Should check if the user already exists based on email.
        if (user == null || user.getName() == null) {
            return null;
        }
        return repository.save(user);
    }
}

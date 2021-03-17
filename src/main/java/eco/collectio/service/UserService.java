package eco.collectio.service;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.User;
import eco.collectio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public List<User> getAllByChallenge(Long challengeId) {
        return repository.findAllByChallenge(challengeId);
    }

    public User create(User user) {
        if (user == null) {
            return null;
        }
        return repository.save(user);
    }
}

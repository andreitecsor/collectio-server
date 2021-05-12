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
    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(String uid) {
        return userRepository.findByUid(uid);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllFollowers(String uid) {
        return userRepository.findAllFollowers(uid);
    }

    public List<User> getAllFollowings(String uid) {
        return userRepository.findAllFollowings(uid);
    }

    public User create(User user) {
        if (user == null || user.getEmail() == null || user.getDisplayName() == null) {
            return null;
        }
        if (getByEmail(user.getEmail()).isPresent()) {
            LOGGER.error("Already existing email address");
            return null;
        }
        return userRepository.save(user);
    }

    public User update(String uid, User newUserDetails) {
        if (newUserDetails == null || newUserDetails.getDisplayName() == null || newUserDetails.getEmail() == null) {
            LOGGER.error("Invalid user to update");
            return null;
        }
        Optional<User> optionalUser = getById(uid);
        if (!optionalUser.isPresent()) {
            LOGGER.error("There is no user to update");
            return null;
        }
        User userToUpdate = optionalUser.get();
        userToUpdate.setDisplayName(newUserDetails.getDisplayName());
        userToUpdate.setEmail(newUserDetails.getEmail());
        return userRepository.save(userToUpdate);
    }
}

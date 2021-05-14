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

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllFollowers(String uid) {
        return userRepository.findAllFollowers(uid);
    }

    public List<User> getAllFollowings(String uid) {
        return userRepository.findAllFollowings(uid);
    }

    public User create(User user) {
        if (user == null || user.getEmail() == null) {
            return null;
        }
        Optional<User> optionalUser = getById(user.getUid());
        if (optionalUser.isPresent()
                && optionalUser.get().getEmail().equals(user.getEmail())) {
            return optionalUser.get();
        }
        return userRepository.save(user);
    }

    public User update(String uid, User newUserDetails) {
        if (newUserDetails == null || uid == null) {
            LOGGER.error("Invalid user to update");
            return null;
        }
        Optional<User> optionalUserByUid = getById(uid);
        if (!optionalUserByUid.isPresent()) {
            LOGGER.error("There is no user to update");
            return null;
        }
        User userToUpdate = optionalUserByUid.get();
        if (newUserDetails.getUsername() != null) {
            Optional<User> optionalUserByUsername = getByUsername(newUserDetails.getUsername());
            if (optionalUserByUsername.isPresent()) {
                LOGGER.error("Already an user with the same username");
                return null;
            }
            userToUpdate.setUsername(newUserDetails.getUsername());
        }

        if (newUserDetails.getDisplayName() != null) {
            userToUpdate.setDisplayName(newUserDetails.getDisplayName());
        }
        return userRepository.save(userToUpdate);
    }
}

package eco.collectio.service;

import eco.collectio.domain.User;
import eco.collectio.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Integer getNoOfInfluence(String id) {
        return userRepository.findNoOfInfluencedByUserId(id);
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
            Optional<User> optionalUserByUsername = getByUsername(newUserDetails.getUsername().toLowerCase());
            if (optionalUserByUsername.isPresent()) {
                LOGGER.error("Already an user with the same username");
                return null;
            }
            userToUpdate.setUsername(newUserDetails.getUsername().toLowerCase());
        }

        if (newUserDetails.getDisplayName() != null) {
            userToUpdate.setDisplayName(newUserDetails.getDisplayName());
        }
        return userRepository.save(userToUpdate);
    }

    public Integer getNoInfluenced(String id) {
        return userRepository.findNoOfInfluencedByUserId(id);
    }

    public Integer getNoOfChallengesStartedBecauseOfUser(String id) {
        return userRepository.findNoOfChallengesStartedBecauseOfUser(id);
    }

    public Set<User> getAllByDisplayName(String displayName) {
        return userRepository.findAllByDisplayName(displayName);
    }

    public Set<User> getAllByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }
}

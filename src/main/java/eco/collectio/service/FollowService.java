package eco.collectio.service;

import eco.collectio.domain.Follow;
import eco.collectio.domain.User;
import eco.collectio.repository.FollowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(FollowService.class);

    @Autowired
    public FollowService(FollowRepository followRepository, UserService userService) {
        this.followRepository = followRepository;
        this.userService = userService;
    }

    public Follow upsert(Long idUserWhoFollows, Long idUserWhoIsFollowed) {
        Follow result = followRepository.findByNodesIds(idUserWhoFollows, idUserWhoIsFollowed);
        if (result == null) {
            Optional<User> userWhoFollows = userService.getById(idUserWhoFollows);
            Optional<User> userWhoIsFollowed = userService.getById(idUserWhoIsFollowed);
            if (!userWhoFollows.isPresent() || !userWhoIsFollowed.isPresent()) {
                logger.error("user who follows(id=" + idUserWhoFollows + ") or user whoIsFollowed(id=" + idUserWhoIsFollowed + ") does not exists");
                return null;
            }
            Follow newFollowRelation = new Follow(userWhoFollows.get(), userWhoIsFollowed.get());
            return followRepository.save(newFollowRelation);
        }
        if (result.getLastTimeUnfollowed() != null) {
            result.followAgain();
            return followRepository.save(result);
        }
        logger.error(result.toString() + " is still active");
        return null;
    }

    public Follow unfollow(Long idUserWhoFollows, Long idUserWhoIsFollowed) {
        Follow result = followRepository.findByNodesIds(idUserWhoFollows, idUserWhoIsFollowed);
        if (result == null || result.getLastTimeUnfollowed() != null) {
            logger.error("The specific FOLLOWS relation does not exists or it's already ended");
            return null;
        }
        result.unfollow();
        return followRepository.save(result);
    }
}

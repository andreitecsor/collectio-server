package eco.collectio.dto;

import eco.collectio.domain.Challenge;
import eco.collectio.domain.PostType;
import eco.collectio.domain.Stage;
import eco.collectio.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDetails implements Serializable {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    private Long postId;
    private PostType postType;
    private User author;
    private LocalDateTime date;
    private Challenge challenge = null;
    private Stage stage = null;
    private User following = null;
}

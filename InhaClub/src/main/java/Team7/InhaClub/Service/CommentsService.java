package Team7.InhaClub.Service;

import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Repository.ClubRepository;
import Team7.InhaClub.Repository.CommentsRepository;
import Team7.InhaClub.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final ClubRepository clubRepository;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(Comments comments) {
        commentsRepository.save(comments);
        return comments.getId();
    }

    public void delete(Comments comments) { commentsRepository.delete(comments);}

    @Transactional
    public Optional<Comments> findById(Long _id) {
        return commentsRepository.findById(_id);
    }

    public String makeAnonymous() {
        StringBuffer stringBuffer = new StringBuffer("익명_");
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            stringBuffer.append((char) r.nextInt() % 10);
            System.out.println(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }
}

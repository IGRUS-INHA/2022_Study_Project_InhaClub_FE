package Team7.InhaClub.Service;

import Team7.InhaClub.Domain.Dto.CommentsRequestDto;
import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Repository.ClubRepository;
import Team7.InhaClub.Repository.CommentsRepository;
import Team7.InhaClub.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final ClubRepository clubRepository;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(CommentsRequestDto _dto) {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        _dto.setCreatedDate(dft.toString());
        _dto.setModifiedDate(dft.toString());
        _dto.setUserName(makeAnonymous());
        Comments comments = _dto.toEntity();
        commentsRepository.save(comments);

        return comments.getId();
    }

    @Transactional
    public Optional<Comments> findById(Long _id) {
        return commentsRepository.findById(_id);
    }

    private String makeAnonymous() {
        StringBuffer stringBuffer = new StringBuffer("asdf");
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            int n = r.nextInt() % 3;

            if (n == 0) { // high alphabet
                stringBuffer.append((char) (r.nextInt(26) + 97));
            } else { // lower alphabet
                stringBuffer.append((char) (r.nextInt(26) + 65));
            }
        }
        return stringBuffer.toString();
    }
}

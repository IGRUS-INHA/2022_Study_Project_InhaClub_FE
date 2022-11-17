package Team7.InhaClub.Service;

import Team7.InhaClub.Domain.Dto.RequestDto.CommentsRequestDto;
import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Repository.ClubRepository;
import Team7.InhaClub.Repository.CommentsRepository;
import Team7.InhaClub.Repository.PostsRepository;
import Team7.InhaClub.Security.EncryptPw;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentsService {
    private final ClubRepository clubRepository;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final EncryptPw encryptPw;
    private final PasswordEncoder passwordEncoder;

    /** qna 저장 */
    @Transactional
    public Long save(CommentsRequestDto _dto) {
        // 시간 관련 변수
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // pw 관련 변수
        String _pw = _dto.getPassword();
        String _salt = encryptPw.genSalt();

        _dto.setPosts(postsRepository.findById(_dto.getPostsId()).orElseThrow(() -> new IllegalArgumentException("post id not found."))); // 상위 post 지정

        // pw 생성
        _dto.setSalt(_salt);
        _dto.setPassword(encryptPw.encodePassword(_salt, _pw));

        // 시간 지정
        _dto.setCreatedDate(ldt.format(dft));
        _dto.setModifiedDate(ldt.format(dft));

        // username 지정
        if (Objects.equals(_dto.getUsername(), "")) {
            _dto.setUsername(makeAnonymous());
        }

        Comments comments = _dto.toEntity(); // comments entity 생성
        commentsRepository.save(comments); // repository 저장
        return comments.getId(); // id return
    }

    @Transactional
    public Comments modify(CommentsRequestDto _dto) {
        if (commentsRepository.findById(_dto.getId()).isEmpty()) {
            log.info(_dto.getId() + " comment not found.");
            throw new IllegalArgumentException("comments not found.");
        } else {
            Comments comments = commentsRepository.findById(_dto.getId()).orElseThrow(() -> new IllegalArgumentException("Comment find error"));
            if (!passwordEncoder.matches(_dto.getPassword(), comments.getPassword())) {
                log.info("password is not matched.");
                throw new IllegalArgumentException("Password is not matched.");
            } else {
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                comments.update(_dto.getContent(), ldt.format(dft));
                return comments;
            }
        }
    }

    @Transactional
    public void delete(CommentsRequestDto _dto) {
        if (commentsRepository.findById(_dto.getId()).isEmpty()) {
            log.info(_dto.getId() + " comment not found.");
            throw new IllegalArgumentException("comments not found.");
        } else {
            Comments comments = commentsRepository.findById(_dto.getId()).orElseThrow(() -> new IllegalArgumentException("Comment find error"));
            if (!passwordEncoder.matches(_dto.getPassword(), comments.getPassword())) {
                log.info("password is not matched.");
                throw new IllegalArgumentException("Password is not matched.");
            } else {
                commentsRepository.delete(comments);
            }
        }
    }

    @Transactional
    public Optional<Comments> findById(Long _id) {
        return commentsRepository.findById(_id);
    }

    public String makeAnonymous() {
        StringBuilder stringBuffer = new StringBuilder("익명_");
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            stringBuffer.append((char) r.nextInt() % 10);
            System.out.println(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }
}

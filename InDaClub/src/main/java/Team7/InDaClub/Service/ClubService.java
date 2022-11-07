package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Dto.ClubDto;
import Team7.InDaClub.Domain.Entity.Club;
import Team7.InDaClub.Domain.Entity.Posts;
import Team7.InDaClub.Repository.ClubRepository;
import Team7.InDaClub.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/** Club 관련 서비스 정의 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final PostsRepository postsRepository;

    /** 동아리 등록 */
    @Transactional
    public Club register(Club _club) {
        validateDuplicateClubName(_club);
        Posts posts = new Posts();
        posts.setContent("asdf");
        postsRepository.save(posts);
        _club.setPosts(posts);
        log.info(_club.getClubName() + "is registered.");
        return clubRepository.save(_club);
    }

    /** 동아리 중복 검사 */
    @Transactional
    private void validateDuplicateClubName(Club _club) {
        clubRepository.findByClubName(_club.getClubName())
                .ifPresent(m -> {
                    throw new IllegalStateException("IllegalStateException - Club name is already exist.");
                });
    }

    @Transactional
    public void clubUpdate(ClubDto clubDto) {
        Club tmpClub = clubRepository.findByClubName(clubDto.getClubName()).orElseThrow(() -> new IllegalArgumentException("Club is not found."));

    }

    @Transactional
    public void clubDelete(ClubDto clubDto) {
        Club tmpClub = clubRepository.findByClubName(clubDto.getClubName()).orElseThrow(() -> new IllegalArgumentException("Club is not found."));
        clubRepository.delete(tmpClub);
    }

    /** 모든 동아리 리스트를 넘김 */
    @Transactional
    public List<Club> findClubs() { return clubRepository.findAll(); }

    /** 고유 id 로 동아리를 찾음 */
    @Transactional
    public Optional<Club> findByClubId(Long _id) { return clubRepository.findById(_id);}

    /** 동아리 이름으로 동아리를 찾음 */
    public Optional<Club> findByClubName(String _name) { return clubRepository.findByClubName(_name); }
}

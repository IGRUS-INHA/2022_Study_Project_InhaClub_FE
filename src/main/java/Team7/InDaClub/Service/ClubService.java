package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Entity.Club;
import Team7.InDaClub.Repository.ClubRepository;
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

    @Transactional
    public Club register(Club _club) {
        validateDuplicateClubName(_club);
        log.info(_club.getName() + "is registered.");
        return clubRepository.save(_club);
    }

    @Transactional
    private void validateDuplicateClubName(Club _club) {
        clubRepository.findByName(_club.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("IllegalStateException - Club name is already exist.");
                });
    }

    @Transactional
    public List<Club> findClubs() { return clubRepository.findAll(); }

    @Transactional
    public Optional<Club> findByClubId(Long _id) { return clubRepository.findById(_id);}

    public Optional<Club> findByClubName(String _name) { return clubRepository.findByName(_name); }
}

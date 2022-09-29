package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Entity.Club;
import Team7.InDaClub.Repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    @Transactional
    public String clubRegister(Club _club) {
        validateDuplicateClubName(_club);
        clubRepository.save(_club);
        return _club.getName();
    }

    @Transactional
    private void validateDuplicateClubName(Club _club) {
        clubRepository.findByName(_club.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @Transactional
    public List<Club> findClubs() { return clubRepository.findAll(); }
    public Optional<Club> findOne(String _name) { return clubRepository.findByName(_name); }
}

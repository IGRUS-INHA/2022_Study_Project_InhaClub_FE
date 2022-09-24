package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Club;
import Team7.InDaClub.Repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public String clubRegister(Club _club) {
        validateDuplicateClubName(_club);
        clubRepository.save(_club);
        return _club.getName();
    }

    private void validateDuplicateClubName(Club _club) {
        clubRepository.findByName(_club.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Club> findClubs() { return clubRepository.findAll(); }
    public Optional<Club> findOne(String _name) { return clubRepository.findByName(_name); }
}

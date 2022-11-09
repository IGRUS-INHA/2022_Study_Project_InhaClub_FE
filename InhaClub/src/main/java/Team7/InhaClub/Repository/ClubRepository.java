package Team7.InhaClub.Repository;

import Team7.InhaClub.Domain.Entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByClubName(String _name);
    Optional<Club> findByRepresentative(String _name);
    Optional<Club> findById(Long id);
}

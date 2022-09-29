package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.Entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByName(String _name);
    Optional<Club> findByRepresentative(String _name);
}

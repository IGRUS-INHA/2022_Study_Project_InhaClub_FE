package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.Entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByUserId(Long _userId);
}

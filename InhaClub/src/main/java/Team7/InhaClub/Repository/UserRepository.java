package Team7.InhaClub.Repository;

import Team7.InhaClub.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String _userId);
    Optional<User> findByUserNickname(String _userNickname);
    Optional<User> findByUserEmail(String _userEmail);
}
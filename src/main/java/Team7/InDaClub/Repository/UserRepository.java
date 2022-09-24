package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String _userId);
    Optional<User> findByUserNickname(String _userNickname);
}
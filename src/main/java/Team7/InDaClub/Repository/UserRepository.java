package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.User;

import java.util.List;
import java.util.Optional;

/** 메모리에서 작동하는 user repository */
public interface UserRepository {
    User save(User _user);
    Optional<User> findById(long _id);
    Optional<User> findByUserId(String _id);
    Optional<User> findByUserNickname(String _nick);
    Optional<User> findByUserEmail(String _email);
    Optional<User> findByUserPhone(String _phone);
    List<User> getAllUser();
}

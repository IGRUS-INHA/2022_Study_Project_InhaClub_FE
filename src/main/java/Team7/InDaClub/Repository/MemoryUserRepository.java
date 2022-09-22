package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
/** 메모리에 저장되는 User Repository */
public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User _user) {
        _user.setId(++sequence);
        store.put(_user.getId(), _user);
        return _user;
    }

    @Override
    public Optional<User> findById(long _id) {
        return Optional.ofNullable(store.get(_id));
    }

    @Override
    public Optional<User> findByUserId(String _id) {
        return store.values().stream()
                .filter(user -> user.getUserId().equals(_id))
                .findAny();
    }

    @Override
    public Optional<User> findByUserNickname(String _nick) {
        return store.values().stream()
                .filter(user -> user.getUserNickname().equals(_nick))
                .findAny();

    }

    @Override
    public Optional<User> findByUserPw(String _pw) {
        return store.values().stream()
                .filter(user -> user.getUserPw().equals(_pw))
                .findAny();
    }

    @Override
    public Optional<User> findByUserEmail(String _email) {
        return store.values().stream()
                .filter(user -> user.getUserEmail().equals(_email))
                .findAny();
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

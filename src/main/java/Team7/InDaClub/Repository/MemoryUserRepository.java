package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
/** 메모리에 저장되는 User Repository */
public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    /*
    private long getSequence() { return this.sequence; }
    private void setSequence(long _seq) { this.sequence = _seq; }
    */

    /** 받아온 유저 데이터를 메모리에 저장 */
    @Override
    public User save(User _user) {
        _user.setId(++sequence);
        store.put(_user.getId(), _user);
        return _user;
    }

    /*
    private void assignSequence(){

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        while(this.findById(this.getSequence()).isPresent())
        {
            this.setSequence(r.nextLong());
        }
    }
    */

    /** 유저의 고유 id로 검색 */
    @Override
    public Optional<User> findById(long _id) {
        return Optional.ofNullable(store.get(_id));
    }

    /** 유저의 id로 검색 */
    @Override
    public Optional<User> findByUserId(String _id) {
        return store.values().stream()
                .filter(user -> user.getUserId().equals(_id))
                .findAny();
    }

    /** 유저의 nickname 로 검색 */
    @Override
    public Optional<User> findByUserNickname(String _nick) {
        return store.values().stream()
                .filter(user -> user.getUserNickname().equals(_nick))
                .findAny();

    }

    /** 유저의 email 로 검색 */
    @Override
    public Optional<User> findByUserEmail(String _email) {
        return store.values().stream()
                .filter(user -> user.getUserEmail().equals(_email))
                .findAny();
    }

    /** 유저의 전화번호로 검색 */
    @Override
    public Optional<User> findByUserPhone(String _phone) {
        return store.values().stream()
                .filter(user -> user.getUserPhone().equals(_phone))
                .findAny();
    }

    /** 모든 유저를 리스트로 (테스트용 함수, 삭제예정) */
    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(store.values());
    }

    /** 메모리 초기화 함수 (테스트용 함수, 삭제예정) */
    public void clearStore() {
        store.clear();
    }
}

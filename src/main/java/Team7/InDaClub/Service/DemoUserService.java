package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.User;
import Team7.InDaClub.Repository.MemoryUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoUserService {
    private final MemoryUserRepository memoryUserRepository = new MemoryUserRepository();

    /** 회원가입 함수 */
    public String join(User _user) {
        validateDuplicateUserId(_user);
        memoryUserRepository.save(_user);
        return _user.getUserId();
    }

    /** 회원가입시 중복 아이디 검사 */
    private void validateDuplicateUserId(User _user) {
        memoryUserRepository.findByUserId(_user.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /** 전체 회원 조회 */
    public List<User> findUsers(){
        return memoryUserRepository.getAllUser();
    }

    /** 유저의 id로 유저를 검색 */
    public Optional<User> findOne(String _userId) {
        return memoryUserRepository.findByUserId(_userId);
    }
}

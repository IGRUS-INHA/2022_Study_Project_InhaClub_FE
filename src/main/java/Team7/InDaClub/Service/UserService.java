package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.User;
import Team7.InDaClub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String join(User _user) {
        validateDuplicateUserId(_user);
        userRepository.save(_user);
        return _user.getUserId();
    }

    private void validateDuplicateUserId(User _user) {
        userRepository.findByUserId(_user.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<User> findUsers() { return userRepository.findAll();}
    public Optional<User> findOne(String _userId) {
        return userRepository.findByUserId(_userId);
    }
}

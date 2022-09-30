package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<User> findUsers() { return userRepository.findAll();}
    @Transactional
    public Optional<User> findOne(String _userId) {
        return userRepository.findByUserId(_userId);
    }
}

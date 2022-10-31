package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Dto.UserDto;
import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /** 유저 리스트 가져오기 */
    @Transactional
    public List<User> findUsers() { return userRepository.findAll();}

    /** userId 로 유저 찾기 */
    @Transactional
    public Optional<User> findByUserId(String _userId) { return userRepository.findByUserId(_userId); }

    /** 유저정보 수정 */
    @Transactional
    public void userUpdate(UserDto userDto) {
        User tmpUser = findByUserId(userDto.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        tmpUser.update(userDto.getUserNickname(), userDto.getUserPhone());
    }

    /** 유저 삭제 */
    @Transactional
    public void userDelete(UserDto userDto) {
        userRepository.delete(findByUserId(userDto.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found.")));
    }


}

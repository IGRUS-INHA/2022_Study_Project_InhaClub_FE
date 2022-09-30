package Team7.InDaClub.Security;

import Team7.InDaClub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** custom 된 UserDetailService 객체 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /** user 의 nickname 으로 user 를 찾는 함수 */
    @Override
    public UserDetails loadUserByUsername(String _userId) throws UsernameNotFoundException {
        return  userRepository.findByUserId(_userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}

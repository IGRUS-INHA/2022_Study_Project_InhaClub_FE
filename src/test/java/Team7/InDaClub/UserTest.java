package Team7.InDaClub;

import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Exception.ErrorCode;
import Team7.InDaClub.Exception.UserIDDuplicateException;
import Team7.InDaClub.Repository.UserRepository;
import Team7.InDaClub.Security.EncryptPw;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserTest {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EncryptPw saltUtil;

    @Test
    public void save() {
        User user = User.builder()
                .userId("case1")
                .userEmail("a@a.com")
                .userPhone("010-1212-2222")
                .userNickname("wnwn")
                .userPw("1q2w3e4r!")
                .build();

        Optional<User> alreadyUser = userRepository.findByUserId(user.getUserId());
        // .orElseThrow(()-> new UserIDDuplicateException("userID duplicated", ErrorCode.USERID_DUPLICATION));

        if(alreadyUser.isPresent())  {
            throw new UserIDDuplicateException("UserID duplicated", ErrorCode.USERID_DUPLICATION);
        }
        else {
            String password = user.getPassword();
            String salt= saltUtil.genSalt();
            user.setSalt(salt);
            user.setUserPw(saltUtil.encodePassword(salt, password));
        }
        userRepository.save(user);

    }

}
package Team7.InhaClub;

import Team7.InhaClub.Domain.Entity.User;
import Team7.InhaClub.Exception.ErrorCode;
import Team7.InhaClub.Exception.UserIDDuplicateException;
import Team7.InhaClub.Repository.UserRepository;
import Team7.InhaClub.Security.EncryptPw;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class UserTest {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EncryptPw saltUtil;

    @Test
    public void save() {

        for (int i = 1; i < 10; i++) {
            User user = User.builder()
                    .userId("case" + i)
                    .userEmail("a" + i + "@a.com")
                    .userPhone("010-1111-11" + i)
                    .userNickname("kim" + i)
                    .userPw(i + "q2w3e4r!")
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

}
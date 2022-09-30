package Team7.InDaClub.Security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/** password 의 암호화를 담당하는 서비스 클래스 */
@Service
public class EncryptPw {

    /** password 를 BCrypt 로 encode 함 */
    public String encodePassword (String _salt, String _pw) {
        return BCrypt.hashpw(_pw, _salt);
    }

    /** password 의 salt 값을 만듬 */
    public String genSalt() {
        return BCrypt.gensalt();
    }
}

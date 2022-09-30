package Team7.InDaClub.Security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptPw {

    public String encodePassword (String _salt, String _pw) {
        return BCrypt.hashpw(_pw, _salt);
    }
    public String genSalt() {
        return BCrypt.gensalt();
    }
}

package Team7.InDaClub.Domain;

import lombok.*;

// import javax.persistence.*;
// import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {

       /** 유저의 고유 ID */
       private long id;

       /** 유저의 로그인 ID */
       private String userId;

       /** 유저의 로그인 PW */
       private String userPw;

       /** 유저의 로그인 닉네임 */
       private String userNickname;

       /** 유저의 이메일 */
       private String userEmail;

       /** 유저의 전화번호 */
       private String userPhone;

       /** 가입된 클럽 */
       //private List<String> joinedClub;

}

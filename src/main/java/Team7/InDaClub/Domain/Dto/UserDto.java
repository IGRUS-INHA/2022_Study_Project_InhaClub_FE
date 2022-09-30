package Team7.InDaClub.Domain.Dto;

import Team7.InDaClub.Domain.Entity.User;
import lombok.*;

/** User 객체의 dto */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;

    /** 유저의 로그인 ID */
    private String userId;

    /** 유저의 로그인 PW */
    private String userPw;

    /** pw 변조를 위한 salt */
    private String salt;

    /** 유저의 로그인 닉네임 */
    private String userNickname;

    /** 유저의 이메일 */
    private String userEmail;

    /** 유저의 전화번호 */
    private String userPhone;

    /** UserDto 객체를 User 객체로 변환하는 함수 */
    public User toEntity() {
        User build = User.builder()
                .id(id)
                .userId(userId)
                .userPw(userPw)
                .salt(salt)
                .userNickname(userNickname)
                .userEmail(userEmail)
                .userPhone(userPhone)
                .build();

        return build;
    }
}

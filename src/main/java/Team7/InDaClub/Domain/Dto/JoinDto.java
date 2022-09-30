package Team7.InDaClub.Domain.Dto;

import Team7.InDaClub.Domain.Entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** 회원가입 페이지에서 받아온 데이터를 이동시킬 때의 dto */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JoinDto {

    @NotBlank
    private String userId;

    @NotBlank
    @Size(min = 8, max = 20)
    private String userPw;

    @NotBlank
    private String userNickname;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPhone;

    /** 객체의 데이터를 User 형태로 만드는 함수 */
    public User toEntity() {
        User build = User.builder()
                .userId(userId)
                .userPw(userPw)
                .userNickname(userNickname)
                .userEmail(userEmail)
                .userPhone(userPhone)
                .build();

        return build;
    }
}

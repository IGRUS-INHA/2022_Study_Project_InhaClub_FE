package Team7.InDaClub.Domain.Dto;

import Team7.InDaClub.Domain.Entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

package Team7.InDaClub.Domain.Dto;

import lombok.*;

/** 로그인 데이터를 이동시킬 때의 Dto */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginDto {
    /** User 의 ID */
    private String userId;
    /** User 의 Password */
    private String userPw;
}

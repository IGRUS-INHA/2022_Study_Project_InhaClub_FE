package Team7.InDaClub.Domain.Dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String userPw;
}

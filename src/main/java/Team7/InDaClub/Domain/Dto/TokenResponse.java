package Team7.InDaClub.Domain.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
}
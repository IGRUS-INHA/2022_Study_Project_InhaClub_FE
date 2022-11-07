package Team7.InDaClub.Domain.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmailRequestDto {
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String userEmail;

    private String code;
}

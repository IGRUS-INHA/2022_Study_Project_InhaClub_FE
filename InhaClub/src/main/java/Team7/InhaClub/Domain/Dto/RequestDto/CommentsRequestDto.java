package Team7.InhaClub.Domain.Dto.RequestDto;

import Team7.InhaClub.Domain.Dto.ResponseDto.CommentsResponseDto;
import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Domain.Entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsRequestDto {
    private Long id;
    private String content;
    private String createdDate;
    private String modifiedDate;
    private String username;
    private Posts posts;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    private String salt;
    private Long postsId;

    public Comments toEntity() {
        return Comments.builder()
                .id(id)
                .content(content)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .posts(posts)
                .username(username)
                .password(password)
                .salt(salt)
                .build();
    }
}

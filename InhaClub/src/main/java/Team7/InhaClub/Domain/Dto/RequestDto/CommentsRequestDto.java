package Team7.InhaClub.Domain.Dto.RequestDto;

import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Domain.Entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsRequestDto {
    private Long id;
    private String content;
    private String createdDate;
    private String modifiedDate;
    private String userName;
    private Posts posts;
    private Long postsId;

    public Comments toEntity() {
        return Comments.builder()
                .id(id)
                .content(content)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .posts(posts)
                .userName(userName)
                .build();
    }

}

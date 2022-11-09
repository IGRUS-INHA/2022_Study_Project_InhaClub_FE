package Team7.InhaClub.Domain.Dto;

import Team7.InhaClub.Domain.Entity.Comments;
import lombok.Getter;

@Getter
public class CommentsResponseDto {
    private final Long id;
    private final String content;
    private final String createdDate;
    private final String modifiedDate;
    private final Long postId;
    private final String userName;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.content = comments.getContent();
        this.createdDate = comments.getCreatedDate();
        this.modifiedDate = comments.getModifiedDate();
        this.postId = comments.getPosts().getId();
        this.userName = comments.getUserName();
    }
}

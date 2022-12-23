package Team7.InhaClub.Domain.Dto.ResponseDto;

import Team7.InhaClub.Domain.Entity.Comments;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentsResponseDto {
    private final Long id;
    private final String content;
    private final String createdDate;
    private final String modifiedDate;
    private final Long postId;
    private final String username;
    private final String password;
    private final List<Comments> children;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.content = comments.getContent();
        this.createdDate = comments.getCreatedDate();
        this.modifiedDate = comments.getModifiedDate();
        this.postId = comments.getPosts().getId();
        this.username = comments.getUsername();
        this.password = comments.getPassword();
        this.children = comments.getChildren();
    }
}

package Team7.InhaClub.Domain.Dto.ResponseDto;

import Team7.InhaClub.Domain.Entity.Posts;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;
    private String content;
    private List<CommentsResponseDto> comments;

    public PostResponseDto(Posts posts) {
        this.id = posts.getId();
        this.content = posts.getContent();
        this.comments = posts.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
}

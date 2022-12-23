package Team7.InhaClub.Domain.Dto.ResponseDto;

import Team7.InhaClub.Domain.Entity.Posts;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String content;
    private List<CommentsResponseDto> comments;

    public void setComments(List<CommentsResponseDto> _comments) {
        this.comments = _comments;
    }

    public PostResponseDto(Posts posts) {
        this.id = posts.getId();
        this.content = posts.getContent();
        /*
        List<CommentsResponseDto> result = new ArrayList<>();
        Map<Long, CommentsResponseDto> map = new HashMap<>();
        posts.getComments().stream().forEach(c -> {
            CommentsResponseDto dto  = new CommentsResponseDto(c);
            map.put(dto.getId(), dto);
            if(c.getParent() != null) map.get(c.getParent().getId()).getChildren().add();
        });
        */
        this.comments = posts.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
}

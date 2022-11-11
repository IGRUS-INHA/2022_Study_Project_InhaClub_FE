package Team7.InhaClub.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "post")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comments> comments;

    public void setContent(String _content) {
        this.content = _content;
    }

}

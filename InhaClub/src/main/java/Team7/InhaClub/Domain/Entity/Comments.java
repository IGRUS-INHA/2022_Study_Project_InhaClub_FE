package Team7.InhaClub.Domain.Entity;

import Team7.InhaClub.Domain.Dto.RequestDto.CommentsRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "comments")
public class Comments {

    /** comments id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** QnA 내용 */
    @Column(columnDefinition = "TEXT")
    private String content;

    /** QnA 생성일 */
    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    /** QnA 수정일 */
    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    /** 이 Comment 가 종속된 Post 의 Id */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    /** 작성자 */
    @Column(nullable = false)
    private String username;

    /** 댓글 삭제용 비밀번호 */
    @Column(nullable = false)
    private String password;

    /** 삭제에 사용될 salt */
    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comments parent;

    @Builder.Default // comments build 시 list 의 초기화를 위해 사용
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comments> children = new ArrayList<>();

    public void update(CommentsRequestDto _dto)
    {
        this.content = "[수정됨]" + _dto.getContent();
        this.modifiedDate = _dto.getModifiedDate();
    }

    public void delete(String _time) {
        this.deleted = true;
        this.content = "삭제된 댓글입니다.";
        this.username = "삭제됨";
        this.modifiedDate = _time;
    }
}

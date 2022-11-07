package Team7.InDaClub.Domain.Entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** QnA 내용 */
    @Column(columnDefinition = "TEXT", nullable = false)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    /** 작성자 */
    @Column(nullable = false)
    private String userName;

}

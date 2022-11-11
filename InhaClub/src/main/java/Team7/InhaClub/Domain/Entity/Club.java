package Team7.InhaClub.Domain.Entity;

import lombok.*;
import org.h2.util.json.JSONObject;

import javax.persistence.*;

/** 동아리 관련 Entity 정의 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "club")
public class Club {

    /** 동아리 고유 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /** 동아리의 이름 */
    @Column(nullable = false, unique = true, length = 100)
    private String clubName;

    /** 동아리 대표자 */
    @Column(nullable = false)
    private String representative;

    /** 모집 여부 */
    @Column(nullable = false)
    private boolean inRecruit;

    /** 관심사 */
    @Column(nullable = false)
    private String interest;

    /** 동아리 방 */
    @Column
    private String room;

    /** 가입 대상 */
    @Column
    private String recruitTarget;

    /** 모집 시작일 */
    @Column
    private String recruitStart;

    /** 모집 종료일 */
    @Column
    private String recruitEnd;

    /** 지원 조건 */
    @Column
    private String applicationConditions;

    /** Club -> Post */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    public void setPosts(Posts _posts) {
        this.posts = _posts;
    }

    public void update() {

    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package Team7.InhaClub.Domain.Entity;

import Team7.InhaClub.Domain.Dto.RequestDto.ClubRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column
    private String sns;

    /** Club -> Post */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "posts_id")
    @JsonIgnore
    private Posts posts;

    @Column
    private String description;

    public void setPosts(Posts _posts) {
        this.posts = _posts;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void update(ClubRequestDto _request) {
        this.clubName = _request.getClubName();
        this.representative = _request.getRepresentative();
        this.sns = _request.getSns();
        this.inRecruit = _request.isInRecruit();
        this.interest = _request.getInterest();
        this.room = _request.getRoom();
        this.recruitTarget = _request.getRecruitTarget();
        this.recruitStart = _request.getRecruitStart();
        this.recruitEnd = _request.getRecruitEnd();
        this.applicationConditions = _request.getApplicationConditions();
        this.description = _request.getDescription();
    }
}

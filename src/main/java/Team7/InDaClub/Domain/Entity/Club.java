package Team7.InDaClub.Domain.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** 동아리 관련 Entity 정의 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private String name;

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

    /**  */
    @Column(columnDefinition = "integer default 0")
    private int view;

    /** 대표자 이름 */
    private String representativeName;

    /** JSON 형식의 클럽 정보 */
    private String clubInfo;

    /** 동아리 정보를 JSON 화 */
    public void updateClubInfo() {
        // 대충 나중에 해야지
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

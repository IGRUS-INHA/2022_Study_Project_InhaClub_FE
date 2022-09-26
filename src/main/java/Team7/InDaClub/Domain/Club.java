package Team7.InDaClub.Domain;

import lombok.*;

import javax.persistence.*;

/** 동아리 관련 Entity 정의 */
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    /** 동아리 SNS */
    @Column
    private String sns;

    /** 동아리의 관심사 */
    @Column(nullable = false)
    private String interest;

    /** 동아리 모집 여부 */
    @Column(nullable = false)
    private Boolean inRecruit;

    /** 동아리 모집 기한 */
    @Column(nullable = false)
    private String recruitDate;

    /** 동아리 모집 대상 */
    @Column(nullable = false)
    private String recruitTarget;

    /** 동아리 방 위치 */
    @Column(nullable = false)
    private String room;

    /** 동아리 대표자 */
    @Column(nullable = false)
    private String representative;

}

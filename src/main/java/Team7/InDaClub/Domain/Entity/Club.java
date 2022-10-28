package Team7.InDaClub.Domain.Entity;

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

    /** 동아리 대표자 */
    @Column(nullable = false)
    private String representative;

    /** 동아리 관련 정보 (json) */
    @Column(nullable = false)
    private String clubInfo;

}

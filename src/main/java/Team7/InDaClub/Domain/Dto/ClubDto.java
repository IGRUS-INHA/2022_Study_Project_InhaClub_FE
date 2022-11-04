package Team7.InDaClub.Domain.Dto;

import Team7.InDaClub.Domain.Entity.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClubDto {

    /** 동아리의 이름 */
    private String name;
    /** 대표자 */
    private String representative;
    /** 모집 여부 */
    private boolean inRecruit;
    /** 관심사 */
    private String interest;
    /** 동아리 SNS Id */
    private String sns;
    /** 동아리 방 */
    private String room;
    /** 가입 대상 */
    private String recruitTarget;
    /** 모집 시작일 */
    private String recruitStart;
    /** 모집 종료일 */
    private String recruitEnd;
    /** 지원 조건 */
    private String applicationConditions;

    public Club toEntity() {
        Club club = Club.builder()
                .name(name)
                .representative(representative)
                .inRecruit(inRecruit)
                .interest(interest)
                .room(room)
                .recruitTarget(recruitTarget)
                .recruitStart(recruitStart)
                .recruitEnd(recruitEnd)
                .applicationConditions(applicationConditions)
                .build();

        return club;
    }
}

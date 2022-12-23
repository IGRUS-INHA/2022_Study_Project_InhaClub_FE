package Team7.InhaClub.Domain.Dto.ResponseDto;

import Team7.InhaClub.Domain.Entity.Club;
import Team7.InhaClub.Domain.Entity.Posts;
import lombok.Getter;

@Getter
public class ClubResponseDto {

    /** 클럷의 id */
    private final Long id;

    /** 동아리의 이름 */
    private final String clubName;

    /** 대표자 */
    private final String representative;

    /** 모집 여부 */
    private final boolean inRecruit;

    /** 관심사 */
    private final String interest;

    /** 동아리 SNS Id */
    private final String sns;

    /** 동아리 방 */
    private final String room;

    /** 가입 대상 */
    private final String recruitTarget;

    /** 모집 시작일 */
    private final String recruitStart;

    /** 모집 종료일 */
    private final String recruitEnd;

    /** 지원 조건 */
    private final String applicationConditions;

    /** 지정된 post */
    private final Posts posts;

    /** 동아리 자체 설명 */
    private final String description;

    public ClubResponseDto(Club club) {
        this.id = club.getId();
        this.clubName = club.getClubName();
        this.representative = club.getRepresentative();
        this.inRecruit = club.isInRecruit();
        this.interest = club.getInterest();
        this.sns = club.getSns();
        this.room = club.getRoom();
        this.recruitTarget = club.getRecruitTarget();
        this.recruitStart = club.getRecruitStart();
        this.recruitEnd = club.getRecruitEnd();
        this.applicationConditions = club.getApplicationConditions();
        this.posts = club.getPosts();
        this.description = club.getDescription();
    }

}

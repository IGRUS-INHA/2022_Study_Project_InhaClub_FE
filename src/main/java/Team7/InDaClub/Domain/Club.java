package Team7.InDaClub.Domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Club {

    /** 동아리의 고유 id */
    public long clubId;

    /** 동아리 이름 */
    private String clubName;

    /** 동아리 대표자 */
    private String clubRep;

    /** 동아리 장소 */
    private String clubRoom;

    public List<String> getClubData() {
        List<String> output = new ArrayList<>();
        output.add(this.getClubName());
        output.add(this.getClubRep());
        output.add(this.getClubRoom());
        return output;
    }

}

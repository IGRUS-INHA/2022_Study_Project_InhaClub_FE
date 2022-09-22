/*
package Team7.InDaClub.Domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// https://dev-coco.tistory.com/85
// https://dev-coco.tistory.com/75

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class DBUser {

    */
/** 유저의 고유 ID *//*

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    */
/** 유저의 로그인 ID *//*

    @Column(length = 45, nullable = false)
    private String userId;

    */
/** 유저의 로그인 PW *//*

    @Column(length = 45, nullable = false)
    private String userPw;

    */
/** 유저의 로그인 닉네임 *//*

    @Column(length = 45, nullable = false)
    private String userNickname;

    @Column(length = 45, nullable = false)
    private String userEmail; // 유저의 이메일

    private List<String> joinedClub; // 가입된 클럽

}
*/

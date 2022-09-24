package Team7.InDaClub.Domain;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
@Setter
@Builder
@Entity(name = "user")
public class User {
    /** 유저의 고유 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /** 유저의 로그인 ID */
    @Column(nullable = false, unique = true, length = 45)
    private String userId;

    /** 유저의 로그인 PW */
    @Column(nullable = false, length = 45)
    private String userPw;

    /** 유저의 로그인 닉네임 */
    @Column(unique = true, length = 100)
    private String userNickname;

    /** 유저의 이메일 */
    @Column(length = 100)
    private String userEmail;

    /** 유저의 전화번호 */
    @Column(length = 100)
    private String userPhone;

}

package Team7.InDaClub.Domain.Entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity(name = "user")
public class User implements UserDetails {

    /** 유저의 고유 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /** 유저의 로그인 ID */
    @Column(nullable = false, unique = true, length = 45)
    private String userId;

    /** 유저의 로그인 PW */
    @Column(nullable = false, length = 200)
    private String userPw;

    /** pw 변조를 위한 salt */
    @Column(nullable = false)
    private String salt;

    /** 유저의 로그인 닉네임 */
    @Column(unique = true, nullable = false, length = 100)
    private String userNickname;

    /** 유저의 이메일 */
    @Column(unique = true, nullable = false, length = 200)
    private String userEmail;

    /** 유저의 전화번호 */
    @Column(length = 100)
    private String userPhone;

    // 이 밑으로는 UserDetails 에서 상속받은 영역

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    @Override
    public String getUsername() {
        return this.userNickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package Team7.InDaClub.Domain.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "auth")
@EntityListeners(AuditingEntityListener.class)
public class Auth {

    /** 현 접속중인 유저의 고유 ID */
    @Id
    @GeneratedValue
    private long id;

    /** 접속중인 유저의 ID */
    @Column(nullable = false)
    private long userId;

    /** 접속중인 유저의 refreshToken */
    @Column(nullable = false)
    private String refreshToken;
}

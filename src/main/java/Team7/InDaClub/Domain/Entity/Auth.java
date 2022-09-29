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
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String refreshToken;
}

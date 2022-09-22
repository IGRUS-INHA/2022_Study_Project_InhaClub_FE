package Team7.InDaClub.RepositoryTest;

import Team7.InDaClub.Domain.User;
import Team7.InDaClub.Repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        User user = new User();
        user.setUserId("case1");
        user.setUserPw("1q2w3e4r!");
        user.setUserNickname("kim");
        user.setUserEmail("a@a.com");

        // when
        repository.save(user);

        // then
        User result = repository.findByUserId(user.getUserId()).get();
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void findById() {
        // given
        User user1 = new User();
        user1.setUserId("case1");
        user1.setUserPw("1q2w3e4r!");
        user1.setUserNickname("kim");
        user1.setUserEmail("a@a.com");
        repository.save(user1);

        User user2 = new User();
        user2.setUserId("case2");
        user2.setUserPw("1q2w3e4r!");
        user2.setUserNickname("lee");
        user2.setUserEmail("b@b.com");
        repository.save(user1);

        // when
        User result = repository.findById(1).get();

        // then
        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findByUserId() {
        // given
        User user1 = new User();
        user1.setUserId("case1");
        user1.setUserPw("1q2w3e4r!");
        user1.setUserNickname("kim");
        user1.setUserEmail("a@a.com");
        repository.save(user1);

        User user2 = new User();
        user2.setUserId("case2");
        user2.setUserPw("1q2w3e4r!");
        user2.setUserNickname("lee");
        user2.setUserEmail("b@b.com");
        repository.save(user1);

        // when
        User result = repository.findByUserId("case1").get();

        // then
        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void getAllUser() {
        // given
        User user1 = new User();
        user1.setUserId("case1");
        user1.setUserPw("1q2w3e4r!");
        user1.setUserNickname("kim");
        user1.setUserEmail("a@a.com");
        repository.save(user1);

        User user2 = new User();
        user2.setUserId("case2");
        user2.setUserPw("1q2w3e4r!");
        user2.setUserNickname("kim");
        user2.setUserEmail("a@a.com");
        repository.save(user1);

        // when
        List<User> result = repository.getAllUser();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}

package Team7.InhaClub.Repository;

import Team7.InhaClub.Domain.Dto.ResponseDto.CommentsResponseDto;
import Team7.InhaClub.Domain.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findById(Long _id);

}

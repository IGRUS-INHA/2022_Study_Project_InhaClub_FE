package Team7.InhaClub.Service;

import Team7.InhaClub.Domain.Entity.Posts;
import Team7.InhaClub.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Optional<Posts> findById(Long _id) {
        return postsRepository.findById(_id);
    }
}

package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Dto.CommentsRequestDto;
import Team7.InDaClub.Domain.Entity.Club;
import Team7.InDaClub.Domain.Entity.Comments;
import Team7.InDaClub.Domain.Entity.Posts;
import Team7.InDaClub.Repository.ClubRepository;
import Team7.InDaClub.Repository.CommentsRepository;
import Team7.InDaClub.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final ClubRepository clubRepository;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(Long _id, CommentsRequestDto _dto) {
        Club club = clubRepository.findById(_id).orElseThrow(() -> new IllegalArgumentException("not found."));
        Posts posts = club.getPosts();

        _dto.setPosts(posts);
        Comments comments = _dto.toEntity();
        commentsRepository.save(comments);

        return comments.getId();
    }
}

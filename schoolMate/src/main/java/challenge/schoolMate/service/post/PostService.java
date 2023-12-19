package challenge.schoolMate.service.post;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.domain.post.PostRepository;
import challenge.schoolMate.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    User user;
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity(user)).getPost_id();
    }
}
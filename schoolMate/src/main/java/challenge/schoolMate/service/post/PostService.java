package challenge.schoolMate.service.post;

import challenge.schoolMate.domain.post.Post;
import challenge.schoolMate.domain.post.PostRepository;
import challenge.schoolMate.web.dto.PostResponseDto;
import challenge.schoolMate.web.dto.PostSaveRequestDto;
import challenge.schoolMate.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getPost_id();
    }

    @Transactional
    public PostResponseDto findById (Long id){
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        return new PostResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        entity.update(requestDto.getTitle(), requestDto.getContents());

        return postRepository.save(entity).getPost_id();
    }

    @Transactional
    public Long delete(Long id){
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id의 게시글입니다."));
        postRepository.deleteById(id);
        return entity.getPost_id();
    }
}
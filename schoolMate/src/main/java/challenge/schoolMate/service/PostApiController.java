package challenge.schoolMate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PatchMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        return postService.delete(id);
    }
}

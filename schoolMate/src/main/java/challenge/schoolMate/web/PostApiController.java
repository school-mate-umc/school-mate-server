package challenge.schoolMate.web;

import challenge.schoolMate.service.post.PostService;
import challenge.schoolMate.web.dto.PostResponseDto;
import challenge.schoolMate.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @GetMapping("/post/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }
}

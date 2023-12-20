package challenge.schoolMate.web;

import challenge.schoolMate.service.post.PostService;
import challenge.schoolMate.web.dto.PostResponseDto;
import challenge.schoolMate.web.dto.PostSaveRequestDto;
import challenge.schoolMate.web.dto.PostUpdateRequestDto;
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

    /*
    `@PathVariable`을 사용해 주소로부터 수정할 게시물의 id를 받아오기
    `@RequestBody`를 사용해서 변경요청 사항을 Json으로 받아오기
    `PostUpdateRequestDto`에 해당 Json 데이터를 받아 Dto로 Service에 전달
     */
    @PatchMapping("/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public Long delete(@PathVariable Long id){
        return postService.delete(id);
    }
}

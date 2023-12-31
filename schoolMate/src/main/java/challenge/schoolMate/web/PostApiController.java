package challenge.schoolMate.web;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.service.UserService;
import challenge.schoolMate.service.post.PostService;
import challenge.schoolMate.web.dto.PostResponseDto;
import challenge.schoolMate.web.dto.PostSaveRequestDto;
import challenge.schoolMate.web.dto.PostUpdateRequestDto;
import challenge.schoolMate.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {


    private final PostService postService;
    private final UserService userService;

    //모든 글 조회
    @GetMapping("/post")
    public List<PostResponseDto> findAllPosts(){
        List<PostResponseDto> posts = postService.findAllPosts();
        return posts;
    }

    @PostMapping("/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto, HttpSession session) {
        //현재 로그인한 사용자의 정보를 세션에서 가져오기
        String userEmail = (String) session.getAttribute("userId");

        if(userEmail==null){
            throw new RuntimeException("로그인이 필요합니다.");
        }

        User loggedInUser = userService.findByEmail(userEmail);

        requestDto.setUser(loggedInUser);

        //게시글 저장
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
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto, HttpSession session) {
        //현재 로그인한 사용자의 정보를 세션에서 가져오기
        String userEmail = (String) session.getAttribute("userId");

        if(userEmail==null){
            throw new RuntimeException("로그인이 필요합니다.");
        }

        // 현재 로그인한 사용자의 정보를 가져오기
        User loggedInUser = userService.findByEmail(userEmail);

        // 게시글 작성자의 정보를 가져오기
        PostResponseDto post = postService.findById(id);
        UserDto postAuthor = post.getUser();

        // 현재 로그인한 사용자가 게시글 작성자와 동일한지 확인
        if (!loggedInUser.equals(postAuthor)) {
            throw new RuntimeException("해당 게시글을 수정할 권한이 없습니다.");
        }

        return postService.update(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public Long delete(@PathVariable Long id, HttpSession session){
        // 현재 로그인한 사용자의 정보를 세션에서 가져오기
        String userEmail = (String) session.getAttribute("userId");

        if (userEmail == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        // 현재 로그인한 사용자의 정보를 가져오기
        User loggedInUser = userService.findByEmail(userEmail);

        // 게시글 작성자의 정보를 가져오기
        PostResponseDto post = postService.findById(id);
        UserDto postAuthor = post.getUser();

        // 현재 로그인한 사용자가 게시글 작성자와 동일한지 확인
        if (!loggedInUser.equals(postAuthor)) {
            throw new RuntimeException("해당 게시글을 삭제할 권한이 없습니다.");
        }

        return postService.delete(id);
    }
}
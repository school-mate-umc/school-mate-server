package challenge.schoolMate.post;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.domain.post.Post;
import challenge.schoolMate.domain.post.PostRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postsRepository;

    //@After //Junit에서 단위 테스트가 끝날 때마다 수행되는 메서드 지정
    public void CleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        User user = new User();
        user.setEmail("tiamo4745@naver.com");

        //테이블 post에 id 값이 있다면 update가 실행되고 없다면 insert 쿼리가 실행됨
        postsRepository.save(Post.builder()
                .title(title)
                .contents(content)
                .user(user)
                .build());

        //when
        List<Post> postsList = postsRepository.findAll();

        //then
        Post posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContents()).isEqualTo(content);
    }
}
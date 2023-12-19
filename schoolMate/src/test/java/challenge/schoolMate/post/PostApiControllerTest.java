package challenge.schoolMate.post;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.domain.post.Post;
import challenge.schoolMate.domain.post.PostRepository;
import challenge.schoolMate.web.dto.PostSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    //@After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        String title = "title";
        String contents = "contents";

        // 사용자 정보를 제공하여 PostSaveRequestDto를 생성
        User user = new User(); // 적절한 사용자 객체 생성 또는 조회

        PostSaveRequestDto requestDto = PostSaveRequestDto
                .builder()
                .title(title)
                .contents(contents)
                .user_id(user.getUser_id()) // User 객체 설정
                .build();

        String url = "http://localhost:" + port + "/post/";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContents()).isEqualTo(contents);
    }
}
package challenge.schoolMate.web.dto;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String contents;
    private Long user_id; // User ID를 Long으로 변경

    @Builder
    public PostSaveRequestDto(String title, String contents, Long user_id) {
        this.title = title;
        this.contents = contents;
        this.user_id = user_id;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .contents(contents)
                .user(User.builder().user_id(user_id).build()) // User 객체를 새로 생성하여 설정
                .build();
    }
}

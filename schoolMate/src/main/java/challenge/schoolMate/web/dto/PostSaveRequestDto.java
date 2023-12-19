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
    private String user_id;

    @Builder
    public PostSaveRequestDto(String title, String contents, String user_id) {
        this.title = title;
        this.contents = contents;
        this.user_id = user_id;
    }

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .build();
    }
}
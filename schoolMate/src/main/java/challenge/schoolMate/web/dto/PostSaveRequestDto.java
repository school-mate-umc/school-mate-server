package challenge.schoolMate.web.dto;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String contents;
    private User user; // User 객체로 변경

    @Builder
    public PostSaveRequestDto(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .build();
    }
}

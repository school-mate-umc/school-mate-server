package challenge.schoolMate.service;

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
    private User user;

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
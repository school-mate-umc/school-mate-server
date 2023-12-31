package challenge.schoolMate.web.dto;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String title;
    private String content;
    private User user;

    @Builder
    public PostResponseDto(Post entity) {
        this.title = entity.getTitle();
        this.content = entity.getContents();
        this.user = entity.getUser();
    }
}

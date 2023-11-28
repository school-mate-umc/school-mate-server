package challenge.schoolMate.service;

import challenge.schoolMate.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String title;
    private String contents; // 변경: 변수명 수정
    private String user; // 변경: 변수명 수정

    @Builder
    public PostResponseDto(Post entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.user = entity.getUser().getEmail(); // 변경: 작성자 필드를 사용자 이메일로 설정
    }
}
package challenge.schoolMate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor //파라미터가 없는 디폴트 생성자를 생성
public class Post {
    public Post(Long user_id, String title, String contents) {
        this.user_id = user_id;
        this.title = title;
        this.contents = contents;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    private Long user_id;

    private String title;

    private String contents;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}

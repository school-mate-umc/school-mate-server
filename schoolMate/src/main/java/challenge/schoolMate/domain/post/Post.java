package challenge.schoolMate.domain.post;

import challenge.schoolMate.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity // Entity 클래스를 절대로 Request/Response 클래스로 사용해서는 안된다.
@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 생성
public class Post extends BaseTimeEntity {

    @Builder
    public Post(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long post_id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 500, nullable = false)
    private String title;

    // 컬럼을 text 로 설정하여 데이터를 추출
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}

package challenge.schoolMate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor //파라미터가 없는 디폴트 생성자를 생성
public class User {
    public User(String user_name, String nickname, String student_number, String major, String email){
        this.user_name = user_name;
        this.nickname = nickname;
        this.student_number = student_number;
        this.major = major;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private Long university_id;

    private String user_name;

    private String nickname;

    private String student_number;

    private String major;

    private String email;

}
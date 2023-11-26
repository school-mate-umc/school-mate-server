package challenge.schoolMate.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor //파라미터가 없는 디폴트 생성자를 생성
public class University {
    public University(String university_name, int university_point) {
        this.university_name = university_name;
        this.university_point = university_point;
    }

    @Id
    @GeneratedValue
    private Long university_id;

    private String university_name;

    private int university_point;
}

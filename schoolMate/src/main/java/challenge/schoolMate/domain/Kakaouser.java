package challenge.schoolMate.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Kakaouser {

    @Id
    private String userid;
    private String nickname;
//    private String gender;
//    private String birth;

}
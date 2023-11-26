package challenge.schoolMate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KakaoLogin {
    public KakaoLogin(String email, String kakao_id, String accessToken, String refreshToken) {
        this.email = email;
        this.kakao_id = kakao_id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Id
    @GeneratedValue
    private Long kakao_login_id;

    private String email;

    private String kakao_id;

    private String accessToken;

    private String refreshToken;

}
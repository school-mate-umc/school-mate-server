package challenge.schoolMate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoController {
    @GetMapping("kakaoTerms")
    public String kakaoConnect() {

        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + "2d0bea1b896afc3ca2001b460bb62a64");
        url.append("&redirect_uri=http://localhost:8080/kakao-login");
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }
}

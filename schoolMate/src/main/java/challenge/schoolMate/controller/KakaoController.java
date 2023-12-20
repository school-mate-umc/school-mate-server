package challenge.schoolMate.controller;

import challenge.schoolMate.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

    @Autowired
    private KakaoService kakaoService;

    @RequestMapping(value = "/kakao-login")
    public String kakaoLogin(@RequestParam("code") String code, Model model , HttpSession session) throws Exception {

        //code로 토큰 받음
        String access_token = kakaoService.getToken(code);

        //토큰으로 사용자 정보 담은 list 가져오기
//        ArrayList<Object> list = kakaoService.getUserInfo(access_token);
        Map<String, Object> userInfo = kakaoService.getUserInfo(access_token);

        //list 모델에 담아 view로 넘김
//        model.addAttribute("list", list);
        model.addAttribute("userInfo", userInfo);

        return "userInfo";
    }
}

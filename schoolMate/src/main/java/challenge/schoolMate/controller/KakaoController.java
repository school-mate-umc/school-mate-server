package challenge.schoolMate.controller;

import challenge.schoolMate.service.KakaoService;
import challenge.schoolMate.service.post.PostService;
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

    @Autowired
    PostService postService;

    @GetMapping("/login")
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
    public String kakaoLogin(@RequestParam("code") String code, Model model, HttpSession session) throws Exception {

        System.out.println("code: " + code);

        //code로 토큰 받음
        String access_token = kakaoService.getToken(code);

        //토큰으로 사용자 정보 담은 list 가져오기
//        ArrayList<Object> list = kakaoService.getUserInfo(access_token);
        Map<String, Object> userInfo = kakaoService.getUserInfo(access_token);

        System.out.println("access_Token: " + access_token);
        //list 모델에 담아 view로 넘김
//        model.addAttribute("list", list);
        model.addAttribute("userInfo", userInfo);

        System.out.println("login Controller : " + userInfo);

        if (userInfo.get("email") != null) {
            //세션에 사용자 정보 저장
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_token);
        }

        return "userInfo";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        String access_Token = (String)session.getAttribute("access_Token");

        if(access_Token != null && !"".equals(access_Token)){
            kakaoService.kakaoLogout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
        }else{
            System.out.println("access_Token is null");
            //return "redirect:/";
        }
        //return "index";
        return "redirect:/";
    }
}
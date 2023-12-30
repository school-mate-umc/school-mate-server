package challenge.schoolMate.service;

import challenge.schoolMate.domain.Kakaouser;
import challenge.schoolMate.domain.User;
import challenge.schoolMate.repository.KakaoRepository;
import challenge.schoolMate.repository.UserRepositoryInterface;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoServiceImpl implements KakaoService {
    @Override
    public String getToken(String code) throws Exception {
        System.out.println(code);
        String access_Token = "";

        //EndPoint URL = API가 서버에서 자원에 접근할 수 있도록 하는 URL
        final String requestUrl = "https://kauth.kakao.com/oauth/token";

        //토큰을 요청할 URL 객체 생성
        URL url = new URL(requestUrl);

        //HTTP 연결 설정
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        //서버로 요청 보내기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=2d0bea1b896afc3ca2001b460bb62a64");
        sb.append("&redirect_uri=http://localhost:8080/kakao-login");
        sb.append("&code=" + code);
        bw.write(sb.toString());
        bw.flush();

        //responseCode : 200이면 성공
        int responseCode = con.getResponseCode();
        System.out.println("responseCode : " + responseCode);

        //서버의 응답 데이터 가져옴
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        //result에 토큰이 포함된 응답데이터를 한줄씩 저장
        while ((line = br.readLine()) != null) {
            result += line;
        }

        //JSON 데이터를 파싱하기 위한 JsonParser
        JsonParser parser = new JsonParser();

        //값 추출을 위해 파싱한 데이터를 JsonElement로 변환
        JsonElement element = parser.parse(result);

        //element에서 access_token 값을 얻어옴
        access_Token = element.getAsJsonObject().get("access_token").getAsString();

        br.close();
        bw.close();

        return access_Token;
    }

    @Autowired
    KakaoRepository kakaoRepository;

    @Autowired
    UserRepositoryInterface userRepositoryInterface;

    public Map<String, Object> getUserInfo(String access_token) throws Exception {

        //추가
        HashMap<String, Object> userInfo = new HashMap<>();
//        ArrayList<Object> list = new ArrayList<Object>();

        final String requestUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);

        BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        while ((line = bf.readLine()) != null) {
            result += line;
        }

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

        //콘솔창 확인 후 필요한 정보 추출
        System.out.println("----------properties"+properties);
        System.out.println("----------kakao_account"+kakao_account);

//        String thumbnail_image = properties.getAsJsonObject().get("thumbnail_image").getAsString();
        String name = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakao_account.getAsJsonObject().get("email").getAsString();
//        String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
//        String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();

//        list.add(thumbnail_image);
//        list.add(email);
//        list.add(name);
//        list.add(birthday);

        //추가
        userInfo.put("nickname", name);
        userInfo.put("email", email);

        //DB 저장
        Kakaouser kakaouser = new Kakaouser(email, name);
        kakaoRepository.save(kakaouser);

        //DB 저장
        User user = User.builder()
                .user_name(name)
                .nickname("temp_nickname")
                .student_number("temp_student_number")
                .major("temp_major")
                .email(email)
                        .build();

        userRepositoryInterface.save(user);

        return userInfo;
    }

    public void kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

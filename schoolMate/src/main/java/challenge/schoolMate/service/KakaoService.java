package challenge.schoolMate.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KakaoService {

    public String getToken(String code) throws Exception ;
//    public ArrayList<Object> getUserInfo(String access_token) throws Exception  ;
    public Map<String, Object> getUserInfo(String access_token) throws Exception  ;

    void kakaoLogout(String accessToken);

}

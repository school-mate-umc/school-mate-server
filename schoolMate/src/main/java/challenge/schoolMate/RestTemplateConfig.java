package challenge.schoolMate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();

        // 추가할 컨버터를 생성하여 리스트에 추가
        converters.add(new MappingJackson2HttpMessageConverter()); // 예시로 MappingJackson2HttpMessageConverter 사용

        // 컨버터 리스트를 RestTemplate에 설정
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}
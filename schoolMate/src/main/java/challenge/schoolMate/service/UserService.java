package challenge.schoolMate.service;

import challenge.schoolMate.domain.User;
import challenge.schoolMate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUserId(Long id){
        return userRepository.findById(id)
                .orElse(null); // 찾지 못하면 null 반환
    }
    public User findByEmail(String email){

        return userRepository.findByEmail(email);
    }

    public User createUser(String email, String name){
        User user = User.builder()
                .user_name(name)
                .nickname("temp_nickname")
                .student_number("temp_student_number")
                .major("temp_major")
                .email(email)
                .build();
        return userRepository.save(user);
    }


}

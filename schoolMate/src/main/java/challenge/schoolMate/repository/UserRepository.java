package challenge.schoolMate.repository;

import challenge.schoolMate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이메일로 조회
    User findByEmail(String email);

}
package challenge.schoolMate.repository;

import challenge.schoolMate.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    public User findById(Long id){
        User user = em.find(User.class, id);
        if (user == null) {
            throw new IllegalArgumentException("해당 ID에 해당하는 사용자를 찾을 수 없습니다. User ID: " + id);
        }
        return user;
    }
}

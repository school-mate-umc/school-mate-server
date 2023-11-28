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
        return em.find(User.class, id);
    }
}

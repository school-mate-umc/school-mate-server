package challenge.schoolMate.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository <Entity 클래스, PK 타입>를 상속하면, @Repository를 추가할 필요도 없이 기본적인 CRUD 메소드가 자동생성
public interface PostRepository extends JpaRepository<Post, Long> {

}

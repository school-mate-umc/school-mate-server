package challenge.schoolMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JpaAuditingConfig.class)
@SpringBootApplication
public class schoolMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(schoolMateApplication.class, args);
	}

}

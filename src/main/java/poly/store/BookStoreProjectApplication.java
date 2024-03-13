package poly.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookStoreProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreProjectApplication.class, args);
	}

}

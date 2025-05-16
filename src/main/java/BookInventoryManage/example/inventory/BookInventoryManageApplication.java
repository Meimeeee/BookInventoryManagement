package BookInventoryManage.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookInventoryManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookInventoryManageApplication.class, args);
		System.out.println("ISBN" + System.currentTimeMillis());
	}

}

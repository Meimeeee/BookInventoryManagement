package BookInventoryManage.example.inventory.Utils.Configs;

import BookInventoryManage.example.inventory.Security.BookInventorySecurityConfiguration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BookInventorySecurityConfiguration.class)
@Data
public class BookInventoryConfiguration {
    @Value("${bookInventory.jwt.secret}")
    private String jwtSecret;

    @Value("${bookInventory.jwt.issuer}")
    private String jwtIssuer;


}

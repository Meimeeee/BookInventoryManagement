package BookInventoryManage.example.inventory.Utils.Configs;

import BookInventoryManage.example.inventory.Security.BISecurityConfiguration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BISecurityConfiguration.class)
@Data
public class BIConfiguration {
    @Value("${bookInventory.jwt.secret}")
    private String jwtSecret;

    @Value("${bookInventory.jwt.issuer}")
    private String jwtIssuer;

    @Value("${bookInventory,google.client_id}")
    private String googleClientId;

}

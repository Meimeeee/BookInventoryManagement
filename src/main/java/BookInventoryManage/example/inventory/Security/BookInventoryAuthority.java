package BookInventoryManage.example.inventory.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
public class BookInventoryAuthority implements GrantedAuthority {

    public static BookInventoryAuthority of(Integer role) {
        return new BookInventoryAuthority(role.toString());
    }

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    private BookInventoryAuthority(String authority) {
        this.authority = authority;
    }
}

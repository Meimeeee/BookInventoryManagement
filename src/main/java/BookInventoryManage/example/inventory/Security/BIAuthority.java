package BookInventoryManage.example.inventory.Security;

import BookInventoryManage.example.inventory.Enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class BIAuthority implements GrantedAuthority {

    public static BIAuthority of(Role role) {
        return new BIAuthority(role.name());
    }

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    private BIAuthority(String authority) {
        this.authority = authority;
    }
}

package BookInventoryManage.example.inventory.Security;

import org.springframework.security.core.GrantedAuthority;

import BookInventoryManage.example.inventory.Enums.Role;
import lombok.Data;

@Data
public class BIAuthority implements GrantedAuthority {

    public static BIAuthority of(Role role) {
        return new BIAuthority(role.toString());
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

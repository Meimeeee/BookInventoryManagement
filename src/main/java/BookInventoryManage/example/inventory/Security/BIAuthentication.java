package BookInventoryManage.example.inventory.Security;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class BIAuthentication implements Authentication {
    private AccountEntity account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(account.getRole());
        return account != null ? List.of(BIAuthority.of(account.getRole())) : List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return account;
    }

    @Override
    public boolean isAuthenticated() {
        return account != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return AccountEntity.class.getName();
    }

    public BIAuthentication(AccountEntity account) {
        this.account = account;
    }
}

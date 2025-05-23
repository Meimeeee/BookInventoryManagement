package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountReposity extends JpaRepository<AccountEntity, Integer> {
    public Optional<AccountEntity> findByEmail(String email);
}

package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    public boolean existsByName(String name);
}

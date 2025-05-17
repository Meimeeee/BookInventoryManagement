package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}

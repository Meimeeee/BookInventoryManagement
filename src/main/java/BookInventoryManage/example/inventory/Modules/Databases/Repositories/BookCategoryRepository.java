package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookCategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategoryEntity, Integer> {
    public void deleteByBook(BookEntity book);
}

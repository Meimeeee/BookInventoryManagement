package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookCategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategoryEntity, Integer> {
    public void deleteByBook(BookEntity book);
    public void deleteByCategory(CategoryEntity category);
}

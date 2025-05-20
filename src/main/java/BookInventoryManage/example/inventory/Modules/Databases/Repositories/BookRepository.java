package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    public List<BookEntity> findByAuthor_NameContainingIgnoreCase(String authorName);
    public List<BookEntity> findByTitleContainingIgnoreCase(String name);
    public List<BookEntity> findByIsbnContainingIgnoreCase(String isbn);

}

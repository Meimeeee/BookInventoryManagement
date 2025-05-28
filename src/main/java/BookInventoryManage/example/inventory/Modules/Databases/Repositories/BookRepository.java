package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    public List<BookEntity> findByAuthor_NameContainingIgnoreCase(String authorName);
    public List<BookEntity> findByTitleContainingIgnoreCase(String name);
    public List<BookEntity> findByIsbnContainingIgnoreCase(String isbn);
//
//    @Query("SELECT b FROM BookEntity b JOIN b.bookCategories bc JOIN bc.category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :category, '%'))")
//    public List<BookEntity> findByCategoryNameContainingIgnoreCase(@Param("category") String category);


}

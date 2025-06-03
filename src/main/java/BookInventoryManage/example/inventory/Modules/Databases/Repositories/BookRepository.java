package BookInventoryManage.example.inventory.Modules.Databases.Repositories;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query("SELECT b FROM book b WHERE b.isbn = :isbn")
    public Optional<BookEntity> findByIsbnCus(@Param("isbn") String isbn);

    @Query("SELECT b FROM book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    public List<BookEntity> findByTitle(@Param("keyword") String title);

    @Query("SELECT b FROM book b WHERE LOWER(b.author.name) LIKE LOWER(CONCAT('%', :authorName, '%'))")
    public List<BookEntity> findByAuthorName(@Param("authorName") String authorName);

}

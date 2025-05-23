package BookInventoryManage.example.inventory.Modules.Book.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ReviewEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookRepository;
import BookInventoryManage.example.inventory.Modules.Review.DTO.ReviewResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private String ISBN;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private AuthorEntity author;
    private List<ReviewResponseDTO> reviews;

    public static BookResponseDTO fromEntity(BookEntity book) {
        return new BookResponseDTO(book.getIsbn(), book.getTitle(), book.getDescription(),
                book.getCreateAt(), book.getUpdateAt(), book.getAuthor(),
                ReviewResponseDTO.fromEntities(book.getReviews()));
    }

    public static List<BookResponseDTO> fromEntites(List<BookEntity> books) {
        List<BookResponseDTO> list = new ArrayList<>();
        for (BookEntity book : books) {
            list.add(fromEntity(book));
        }
        return list;
    }
}

package BookInventoryManage.example.inventory.Modules.Book.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ReviewEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookRepository;
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
    private List<ReviewEntity> reviews;

//    public static BookResponseDTO fromEntity(BookEntity book){
//
//
//    }
}

package BookInventoryManage.example.inventory.Modules.Author.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    private Integer Id;
    private String name;
    private LocalDate dob;
    private String bio;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<String> books;

    public static AuthorResponseDTO fromEntity(AuthorEntity author) {
        List<String> list = new ArrayList<>();
        for (BookEntity book : author.getBooks()) {
            list.add(book.getTitle());
        }
        return new AuthorResponseDTO(author.getId(), author.getName(), author.getDob(), author.getBio(), author.getCreatedAt(), author.getUpdatedAt(), list);
    }

    public static List<AuthorResponseDTO> fromEntities(List<AuthorEntity> authors) {
        List<AuthorResponseDTO> list = new ArrayList<>();
        for (AuthorEntity author : authors) {
            list.add(fromEntity(author));
        }
        return list;
    }
}

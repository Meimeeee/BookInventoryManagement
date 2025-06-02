package BookInventoryManage.example.inventory.Modules.Book.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequestDTO {
    @NotBlank(message = "Title is required !!")
    @Size(max = 150, message = "Title must not exceed 150 characters")
    private String title;

    @NotBlank(message = "Description is required !!")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotEmpty(message = "At least one category must be selected")
    private List<@NotNull(message = "Category ID must not be null") Integer> categoryIds;

    @NotNull(message = "Author ID is required !!")
    private Integer authorID;
}

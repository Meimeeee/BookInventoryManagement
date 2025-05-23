package BookInventoryManage.example.inventory.Modules.Review.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequestDTO {

    @NotBlank(message = "Content is required !!")
    @Size(max = 1000, message = "Content must not exceed 1000 characters !!")
    private String content;

    @Min(value = 1, message = "Rating must be at least 1 !!")
    @Max(value = 10, message = "Rating must be at most 10 !!")
    private Integer rate;

    @NotNull(message = "Book ID is required !!")
    private Integer bookId;
}

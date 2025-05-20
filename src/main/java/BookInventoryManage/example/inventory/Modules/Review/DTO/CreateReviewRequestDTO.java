package BookInventoryManage.example.inventory.Modules.Review.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Min(1)
    @Max(10)
    private Integer rate;
}

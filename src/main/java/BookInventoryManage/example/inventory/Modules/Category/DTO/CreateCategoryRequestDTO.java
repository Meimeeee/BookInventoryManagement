package BookInventoryManage.example.inventory.Modules.Category.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequestDTO {
    @NotBlank(message = "Name is required !!")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Description is required !!")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
}

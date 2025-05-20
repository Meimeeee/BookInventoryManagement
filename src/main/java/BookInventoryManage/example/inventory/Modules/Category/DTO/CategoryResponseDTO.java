package BookInventoryManage.example.inventory.Modules.Category.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Integer Id;
    private String name;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static CategoryResponseDTO fromEntity(CategoryEntity category) {
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getDescription(), category.getCreateAt(), category.getUpdateAt());
    }

    public static List<CategoryResponseDTO> fromEntities(List<CategoryEntity> categoryEntities) {
        List<CategoryResponseDTO> list = new ArrayList<>();
        for (CategoryEntity category : categoryEntities) {
            list.add(fromEntity(category));
        }
        return list;
    }
}

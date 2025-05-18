package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import BookInventoryManage.example.inventory.Modules.Category.DTO.CreateCategoryRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer Id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 250)
    private String description;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "category")
    private List<BookCategoryEntity> bookCategories;

    public CategoryEntity(CreateCategoryRequestDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
    }
}

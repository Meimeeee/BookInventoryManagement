package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "book_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_category_id")
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public BookCategoryEntity(BookEntity book, CategoryEntity category) {
        this.book = book;
        this.category = category;
    }


}

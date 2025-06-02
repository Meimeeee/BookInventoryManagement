package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import BookInventoryManage.example.inventory.Modules.Book.DTO.CreateBookRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer Id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(unique = true, nullable = false)
    private String isbn;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    //    relation
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @OneToMany(mappedBy = "book")
    private List<BookCategoryEntity> bookCategories;

    @OneToMany(mappedBy = "book")
    private List<ReviewEntity> reviews;

    @PrePersist
    public void prePersist() {
        if (this.isbn == null || this.isbn.isEmpty()) {
            this.isbn = "ISBN" + System.currentTimeMillis();
        }
    }

    public BookEntity(CreateBookRequestDTO dto, AuthorEntity author) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.author = author;
    }
}

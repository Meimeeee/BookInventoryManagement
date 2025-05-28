package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import BookInventoryManage.example.inventory.Modules.Review.DTO.CreateReviewRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "review")
@Data
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer Id;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer rate;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AccountEntity user;

    public ReviewEntity(CreateReviewRequestDTO dto, BookEntity book, AccountEntity account) {
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.book = book;
        this.user = account;
    }

}

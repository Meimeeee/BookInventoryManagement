package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "userr")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer Id;

    @Email
    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 150, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String fname;

    @Column(length = 50, nullable = false)
    private String lname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dob;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private Integer role;
//    user:  1
//    admin: 2

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;


    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = 1;
        }
    }


}

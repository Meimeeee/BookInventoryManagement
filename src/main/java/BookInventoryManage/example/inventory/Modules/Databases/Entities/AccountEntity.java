package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import BookInventoryManage.example.inventory.Modules.Account.DTO.CreateAccountRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer Id;

    @Email
    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 150, nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer role;
//    guest
//    user:   1
//    author: 2
//    admin:  0

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileEntity profile;

    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = 1;
        }
    }

    public AccountEntity(CreateAccountRequestDTO dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.role = dto.getRole();
        this.profile = new ProfileEntity(dto.getProfile(), this);
    }


}

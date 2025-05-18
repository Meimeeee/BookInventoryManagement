package BookInventoryManage.example.inventory.Modules.Databases.Entities;

import BookInventoryManage.example.inventory.Modules.Profile.DTO.CreateProfileRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer Id;

    @Column(length = 50, nullable = false)
    private String fname;

    @Column(length = 50, nullable = false)
    private String lname;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate dob;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "profile")
    private AccountEntity account;

    public ProfileEntity(CreateProfileRequestDTO dto, AccountEntity account){
        this.fname = dto.getFname();
        this.lname = dto.getLname();
        this.dob = dto.getDob();
        this.account = account;
    }

}

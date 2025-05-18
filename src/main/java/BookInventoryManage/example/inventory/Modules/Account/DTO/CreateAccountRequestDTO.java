package BookInventoryManage.example.inventory.Modules.Account.DTO;

import BookInventoryManage.example.inventory.Modules.Profile.DTO.CreateProfileRequestDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDTO {
    @NotBlank(message = "Email is required !!")
    @Email
    private String email;

    @NotBlank(message = "Password is required !!")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one digit, and one special character"
    )
    private String password;

    @Min(value = 0, message = "Role must be at least 0")
    @Max(value = 2, message = "Role must be at most 2")
    private Integer role;
    private CreateProfileRequestDTO profile;
}

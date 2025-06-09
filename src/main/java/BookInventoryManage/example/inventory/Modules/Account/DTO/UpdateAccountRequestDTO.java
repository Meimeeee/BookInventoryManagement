package BookInventoryManage.example.inventory.Modules.Account.DTO;

import BookInventoryManage.example.inventory.Modules.Profile.DTO.CreateProfileRequestDTO;
import BookInventoryManage.example.inventory.Modules.Profile.DTO.UpdateProfileRequestDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequestDTO {
    @NotBlank(message = "Password is required !!")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one digit, and one special character"
    )
    private String oldPassword;

    @NotBlank(message = "Password is required !!")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one digit, and one special character"
    )
    private String newPassword;
}

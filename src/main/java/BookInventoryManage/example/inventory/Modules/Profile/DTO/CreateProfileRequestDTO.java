package BookInventoryManage.example.inventory.Modules.Profile.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileRequestDTO {
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @NotBlank(message = "First name is required !!")
    private String fname;

    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @NotBlank(message = "Last name is required !!")
    private String lname;

    @Past(message = "Date of birth must be in past !!")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
}

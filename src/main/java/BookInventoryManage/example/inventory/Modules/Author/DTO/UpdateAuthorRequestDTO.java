package BookInventoryManage.example.inventory.Modules.Author.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuthorRequestDTO {
    private Optional<String> name;

    @Past(message = "Date of birth must be in the past !!")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Size(min = 0, max = 300, message = "Biography must not exceed 300 characters !!")
    private String bio;
}

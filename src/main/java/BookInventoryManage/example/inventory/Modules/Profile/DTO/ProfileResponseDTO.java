package BookInventoryManage.example.inventory.Modules.Profile.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.ProfileEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private Integer profileID;
    private String fname;
    private String lname;
    private LocalDate dob;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static ProfileResponseDTO fromEntity(ProfileEntity profile) {
        return new ProfileResponseDTO(profile.getId(), profile.getFname(), profile.getLname(), profile.getDob(), profile.getCreateAt(), profile.getUpdateAt());
    }

    public static List<ProfileResponseDTO> fromEntites(List<ProfileEntity> entities) {
        List<ProfileResponseDTO> profiles = new ArrayList<>();
        for (ProfileEntity profile : entities) {
            profiles.add(fromEntity(profile));
        }
        return profiles;
    }

}

package BookInventoryManage.example.inventory.Modules.Account.DTO;

import BookInventoryManage.example.inventory.Enums.Role;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ProfileEntity;
import BookInventoryManage.example.inventory.Modules.Profile.DTO.ProfileResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private Integer accountID;
    private String email;
    private Role role;
    private ProfileResponseDTO profile;

    public static AccountResponseDTO fromEntity(AccountEntity account) {
        return new AccountResponseDTO(account.getId(), account.getEmail(), account.getRole(), ProfileResponseDTO.fromEntity(account.getProfile()));
    }

    public static List<AccountResponseDTO> fromEntities(List<AccountEntity> entities) {
        List<AccountResponseDTO> list = new ArrayList<>();
        for (AccountEntity account : entities) {
            list.add(fromEntity(account));
        }
        return list;
    }


}

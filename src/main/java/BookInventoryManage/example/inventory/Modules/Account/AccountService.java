package BookInventoryManage.example.inventory.Modules.Account;

import BookInventoryManage.example.inventory.Enums.Role;
import BookInventoryManage.example.inventory.Modules.Account.DTO.CreateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.ReviewRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class AccountService {

    @Autowired
    AccountReposity accountReposity;

    @Autowired
    ReviewRepository reviewRepository;


    public void registerAccount(CreateAccountRequestDTO dto) {
        AccountEntity acc = new AccountEntity(dto);
        accountReposity.save(acc);
    }

    public AccountEntity getAccountByID(Integer accID) {
        if (accID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account ID must not be empty !!");
        }
        Optional<AccountEntity> OptAcc = accountReposity.findById(accID);
        if (OptAcc.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account by Id" + accID);
        } else return OptAcc.get();
    }


    public void delete_user() {
        SecurityContext context = SecurityContextHolder.getContext();
        AccountEntity currentAcc = (AccountEntity) context.getAuthentication().getPrincipal();
        reviewRepository.deleteByUser(currentAcc);
        accountReposity.delete(currentAcc);
    }

    public void delete_admin(Integer id) {
        AccountEntity acc = getAccountByID(id);
        reviewRepository.deleteByUser(acc);
        accountReposity.delete(acc);
    }

    public List<AccountEntity> getListProfile() {
        List<AccountEntity> list = accountReposity.findAll();
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any account !!");
        }
        return list;
    }

}

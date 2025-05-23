package BookInventoryManage.example.inventory.Modules.Account;

import BookInventoryManage.example.inventory.Modules.Account.DTO.CreateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Account.DTO.UpdateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ProfileEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.ReviewRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void updateAccount(Integer accountID, UpdateAccountRequestDTO dto) {
        Optional<AccountEntity> OptAcc = accountReposity.findById(accountID);
        if (OptAcc.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account !!");
        else {
            AccountEntity acc = OptAcc.get();
            acc.setPassword(dto.getPassword());
            accountReposity.save(acc);
        }
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

    public void deleteAccount(Integer Id) {
        AccountEntity account = getAccountByID(Id);
        reviewRepository.deleteByUser(account);
        accountReposity.delete(account);
    }


}

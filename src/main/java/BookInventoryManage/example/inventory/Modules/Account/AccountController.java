package BookInventoryManage.example.inventory.Modules.Account;

import BookInventoryManage.example.inventory.Modules.Account.DTO.AccountResponseDTO;
import BookInventoryManage.example.inventory.Modules.Account.DTO.CreateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Account.DTO.UpdateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Category.DTO.CategoryResponseDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/")
    ResponseEntity registerAccount(@RequestBody @Valid CreateAccountRequestDTO dto) {
        accountService.registerAccount(dto);
        return new ResponseEntity("Register Successfully !!", HttpStatus.OK);
    }

    //    list all accounts
    @GetMapping("/")
    ResponseEntity getListProfile() {
        List<AccountEntity> list = accountService.getListProfile();
        return new ResponseEntity(AccountResponseDTO.fromEntities(list), HttpStatus.OK);
    }


}

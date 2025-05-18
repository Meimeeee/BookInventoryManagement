package BookInventoryManage.example.inventory.Modules.Account;

import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AccountReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    AccountService accountService;




}

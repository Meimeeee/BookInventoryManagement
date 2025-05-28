package BookInventoryManage.example.inventory.Modules.Auth;

import BookInventoryManage.example.inventory.Modules.Account.AccountService;
import BookInventoryManage.example.inventory.Modules.Account.DTO.AccountResponseDTO;
import BookInventoryManage.example.inventory.Modules.Account.DTO.UpdateAccountRequestDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.BasicLoginDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.GoogleLoginDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.TokenResponseDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Profile.DTO.UpdateProfileRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    private ResponseEntity basicLogin(@RequestBody BasicLoginDTO dto) {
        TokenResponseDTO token = authService.basicLogin(dto);
        return new ResponseEntity(token, HttpStatus.OK);
    }

    @PostMapping("/google")
    private ResponseEntity googleLogin(@RequestBody GoogleLoginDTO dto) {
        String accessToken = authService.googleLogin(dto);
        return new ResponseEntity(accessToken, HttpStatus.OK);
    }

    @GetMapping("/profile")
    private ResponseEntity getProfile() {
        AccountEntity account = authService.getProfile();
        return new ResponseEntity(AccountResponseDTO.fromEntity(account), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    private ResponseEntity updateAccount(@RequestBody @Valid UpdateAccountRequestDTO dto) {
        authService.updateAccount(dto);
        return new ResponseEntity("Updated !!", HttpStatus.OK);
    }

    @PutMapping("/update-account")
    private ResponseEntity updateProfile(@RequestBody @Valid UpdateProfileRequestDTO dto) {
        authService.updateProfile(dto);
        return new ResponseEntity("Updated !!", HttpStatus.OK);
    }

    @DeleteMapping("/{accountID}")
    ResponseEntity deleteAccount(@PathVariable("accountID") Integer Id) {
        accountService.deleteAccount(Id);
        return new ResponseEntity("Deleted !!", HttpStatus.OK);
    }

}

package BookInventoryManage.example.inventory.Modules.Auth;

import BookInventoryManage.example.inventory.Modules.Auth.DTO.BasicLoginDTO;
import BookInventoryManage.example.inventory.Modules.Auth.DTO.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    private ResponseEntity basicLogin(@RequestBody BasicLoginDTO dto) {
        TokenResponseDTO token = authService.basicLogin(dto);
        return new ResponseEntity(token, HttpStatus.OK);
    }

}

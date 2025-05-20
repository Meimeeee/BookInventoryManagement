package BookInventoryManage.example.inventory.Modules.Author;

import BookInventoryManage.example.inventory.Modules.Author.DTO.AuthorResponseDTO;
import BookInventoryManage.example.inventory.Modules.Author.DTO.CreateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Author.DTO.UpdateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/")
    ResponseEntity createAuthor(@RequestBody @Valid CreateAuthorRequestDTO dto) {
        authorService.createAuthor(dto);
        return new ResponseEntity("Add !!", HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity ListALlAuthor() {
        List<AuthorEntity> list = authorService.listAllAuthors();
        return new ResponseEntity(AuthorResponseDTO.fromEntities(list), HttpStatus.OK);
    }

    @PutMapping("/{authorId}")
    ResponseEntity updateAuthorById(@RequestBody @Valid UpdateAuthorRequestDTO dto, @PathVariable("authorId") Integer Id) {
        authorService.updateAuthorById(Id, dto);
        return new ResponseEntity("Updated !!", HttpStatus.OK);
    }

}

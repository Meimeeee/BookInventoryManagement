package BookInventoryManage.example.inventory.Modules.Author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BookInventoryManage.example.inventory.Modules.Author.DTO.AuthorResponseDTO;
import BookInventoryManage.example.inventory.Modules.Author.DTO.CreateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Author.DTO.UpdateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.DelectionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    DelectionService delectionService;

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

    @DeleteMapping("/{authorID}")
    ResponseEntity deleteAuthorById(@PathVariable("authorID") Integer Id) {
        delectionService.deleteAuthorByID(Id);
        return new ResponseEntity("Deleted !!", HttpStatus.OK);
    }
}

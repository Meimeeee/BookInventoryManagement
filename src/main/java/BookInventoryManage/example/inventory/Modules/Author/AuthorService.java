package BookInventoryManage.example.inventory.Modules.Author;

import BookInventoryManage.example.inventory.Modules.Author.DTO.CreateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Author.DTO.UpdateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Book.BookService;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AuthorRepository;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public void createAuthor(CreateAuthorRequestDTO dto) {
        if (dto.getBio() == null || dto.getBio().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author bio must not be blank or whitespace only");
        }
        AuthorEntity author = new AuthorEntity(dto);
        authorRepository.save(author);
    }

    public AuthorEntity getAuthorByID(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author ID list must not be empty !!");
        }
        Optional<AuthorEntity> OptAuthor = authorRepository.findById(Id);
        if (OptAuthor.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found author !!");
        else return OptAuthor.get();
    }

    public List<AuthorEntity> listAllAuthors() {
        List<AuthorEntity> list = authorRepository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any author !!");
        return list;
    }

    public void updateAuthorById(Integer Id, UpdateAuthorRequestDTO dto) {
        AuthorEntity author = getAuthorByID(Id);
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author name must not be blank or whitespace only !!");
        }
        author.setName(dto.getName());

        if (dto.getDob() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date of birth must not be null !!");
        }
        author.setDob(dto.getDob());

        if (dto.getBio() == null || dto.getBio().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author name must not be blank or whitespace only !!");
        }
        author.setBio(dto.getBio());
        authorRepository.save(author);
    }



}

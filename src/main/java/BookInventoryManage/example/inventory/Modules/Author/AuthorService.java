package BookInventoryManage.example.inventory.Modules.Author;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import BookInventoryManage.example.inventory.Modules.Author.DTO.CreateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Author.DTO.UpdateAuthorRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AuthorRepository;

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
        if (OptAuthor.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found author by Id " + Id + " !!");
        else return OptAuthor.get();
    }

    public List<AuthorEntity> listAllAuthors() {
        List<AuthorEntity> list = authorRepository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any author !!");
        return list;
    }

    public void updateAuthorById(Integer Id, UpdateAuthorRequestDTO dto) {
        AuthorEntity author = getAuthorByID(Id);
        boolean isUpdate = false;
        if (dto.getName() != null && !dto.getName().get().trim().isEmpty()) {
            author.setName(dto.getName().get());
            isUpdate = true;
        }

        if (dto.getDob() != null) {
            author.setDob(dto.getDob());
            isUpdate = true;
        }

        if (dto.getBio() != null && !dto.getBio().trim().isEmpty()) {
            author.setBio(dto.getBio());
            isUpdate = true;
        }
        
        if (isUpdate == true) {
            authorRepository.save(author);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No information provided for update !!");
        }

    }


}

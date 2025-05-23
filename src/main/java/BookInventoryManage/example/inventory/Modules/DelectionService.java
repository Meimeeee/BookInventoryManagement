package BookInventoryManage.example.inventory.Modules;

import BookInventoryManage.example.inventory.Modules.Author.AuthorService;
import BookInventoryManage.example.inventory.Modules.Book.BookService;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AuthorRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelectionService {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;


    public void deleteAuthorByID(Integer Id) {
        AuthorEntity author = authorService.getAuthorByID(Id);
        List<BookEntity> listBooks = bookService.listBookByAuthorName(author.getName());
        for (BookEntity book : listBooks) {
            bookService.deleteBookById(book.getId());
        }
        authorRepository.delete(author);
    }
}

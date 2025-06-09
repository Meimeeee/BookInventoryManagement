package BookInventoryManage.example.inventory.Modules.Author;

import BookInventoryManage.example.inventory.Modules.Book.BookService;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletionService {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;


    public void deleteAuthorByID(Integer Id) {
        AuthorEntity author = authorService.getAuthorByID(Id);
        List<BookEntity> listBooks = bookService.listBookByAuthorName(author.getName(), false);
        if (listBooks != null) {
            for (BookEntity book : listBooks) {
                bookService.deleteBookById(book.getId());
            }
        }
        authorRepository.delete(author);
    }
}

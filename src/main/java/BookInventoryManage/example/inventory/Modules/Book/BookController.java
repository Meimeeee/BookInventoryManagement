package BookInventoryManage.example.inventory.Modules.Book;

import BookInventoryManage.example.inventory.Modules.Book.DTO.BookResponseDTO;
import BookInventoryManage.example.inventory.Modules.Book.DTO.CreateBookRequestDTO;
import BookInventoryManage.example.inventory.Modules.Book.DTO.UpdateBookRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/")
    ResponseEntity addBook(@RequestBody @Valid CreateBookRequestDTO dto) {
        bookService.addBook(dto);
        return new ResponseEntity("Added !!", HttpStatus.OK);
    }

    @PutMapping("/{bookID}")
    ResponseEntity updateBook(@RequestBody @Valid UpdateBookRequestDTO dto, @PathVariable("bookID") Integer Id) {
        bookService.updateBook(Id, dto);
        return new ResponseEntity("Updated !!", HttpStatus.OK);
    }

    @DeleteMapping("/{bookID}")
    ResponseEntity deleteBook(@PathVariable("bookID") Integer id) {
        bookService.deleteBookById(id);
        return new ResponseEntity("Deleted !!", HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity listAllBook() {
        List<BookEntity> list = bookService.listAllBooks();
        return new ResponseEntity(BookResponseDTO.fromEntites(list), HttpStatus.OK);
    }

    //    search
    @GetMapping("/author/{authorName}")
    ResponseEntity listBookByAuthorName(@PathVariable("authorName") String authorName) {
        List<BookEntity> list = bookService.listBookByAuthorName(authorName, true);
        return new ResponseEntity(BookResponseDTO.fromEntites(list), HttpStatus.OK);
    }

    @GetMapping("/{bookName}")
    ResponseEntity listBookByBookName(@PathVariable("bookName") String name) {
        List<BookEntity> list = bookService.listBookByName(name);
        return new ResponseEntity(BookResponseDTO.fromEntites(list), HttpStatus.OK);
    }

    @GetMapping("/isbn/{ISBN}")
    ResponseEntity listBookByISBN(@PathVariable("ISBN") String isbn) {
        BookEntity b = bookService.listBookByISBN(isbn);
        return new ResponseEntity(BookResponseDTO.fromEntity(b), HttpStatus.OK);
    }
}

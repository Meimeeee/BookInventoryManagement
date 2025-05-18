package BookInventoryManage.example.inventory.Modules.Book;

import BookInventoryManage.example.inventory.Modules.Book.DTO.CreateBookRequestDTO;
import BookInventoryManage.example.inventory.Modules.Book.DTO.UpdateBookRequestDTO;
import BookInventoryManage.example.inventory.Modules.Category.CategoryService;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookCategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookCategoryRepository;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookRepository;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookCategoryRepository bookCategoryRepository;


    public void addBook(CreateBookRequestDTO dto) {
        List<CategoryEntity> listCate = categoryService.getListCateByIds(dto.getCategoryIds());
        BookEntity book = new BookEntity(dto);
        book = bookRepository.save(book);
        List<BookCategoryEntity> bookCategoryEntities = new ArrayList<>();
        for (CategoryEntity category : listCate) {
            bookCategoryEntities.add(new BookCategoryEntity(book, category));
        }
        bookCategoryRepository.saveAll(bookCategoryEntities);
    }

    //    nếu thay vì truyền só thì truyền kí tự có làm sao ?
    public BookEntity getBookById(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category ID list must not be empty.");
        }
        Optional<BookEntity> OptBook = bookRepository.findById(Id);
        if (OptBook.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found book !!");
        else return OptBook.get();
    }

    public void updateBook(Integer bookId, UpdateBookRequestDTO dto) {
        BookEntity book = getBookById(bookId);
        book.setTitle(dto.getTile());
        book.setDescription(dto.getDescription());

        bookCategoryRepository.deleteByBook(book);

        List<CategoryEntity> listCate = categoryService.getListCateByIds(dto.getCategoryIds());
        List<BookCategoryEntity> bookCategoryEntities = new ArrayList<>();
        for (CategoryEntity category : listCate) {
            bookCategoryEntities.add(new BookCategoryEntity(book, category));
        }
        bookRepository.save(book);
        bookCategoryRepository.saveAll(bookCategoryEntities);
    }

    public List<BookEntity> listAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        if (books.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book !!");
        else return books;
    }

    public void deleteBookById(Integer Id) {
        BookEntity book = getBookById(Id);
        bookRepository.delete(book);
        bookCategoryRepository.deleteByBook(book);
    }

}

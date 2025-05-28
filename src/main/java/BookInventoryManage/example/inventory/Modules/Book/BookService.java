package BookInventoryManage.example.inventory.Modules.Book;

import BookInventoryManage.example.inventory.Modules.Author.AuthorService;
import BookInventoryManage.example.inventory.Modules.Book.DTO.CreateBookRequestDTO;
import BookInventoryManage.example.inventory.Modules.Book.DTO.UpdateBookRequestDTO;
import BookInventoryManage.example.inventory.Modules.Category.CategoryService;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AuthorEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookCategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookCategoryRepository;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookRepository;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    AuthorService authorService;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    ReviewRepository reviewRepository;


    @Transactional
    public void addBook(CreateBookRequestDTO dto) {
        List<CategoryEntity> listCate = categoryService.getListCateByIds(dto.getCategoryIds());
        AuthorEntity author = authorService.getAuthorByID(dto.getAuthor().getId());
        BookEntity book = new BookEntity(dto, author);
        book = bookRepository.save(book);
        List<BookCategoryEntity> bookCategoryEntities = new ArrayList<>();
        for (CategoryEntity category : listCate) {
            bookCategoryEntities.add(new BookCategoryEntity(book, category));
        }
        bookCategoryRepository.saveAll(bookCategoryEntities);
    }

    @Transactional
    public void updateBook(Integer bookId, UpdateBookRequestDTO dto) {
        BookEntity book = getBookById(bookId);
        if (dto.getTile() == null || dto.getTile().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title must not be blank or whitespace only !!");
        }
        book.setTitle(dto.getTile());
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description must not be blank or whitespace only !!");
        }
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

    @Transactional
    public void deleteBookById(Integer Id) {
        BookEntity book = getBookById(Id);
        bookCategoryRepository.deleteByBook(book);
        reviewRepository.deleteByBook(book);
        bookRepository.delete(book);
    }

    public List<BookEntity> listAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        if (books.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book !!");
        else return books;
    }

    //    search
    public BookEntity getBookById(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book ID list must not be empty !!");
        }
        Optional<BookEntity> OptBook = bookRepository.findById(Id);
        if (OptBook.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found book !!");
        else return OptBook.get();
    }

    public List<BookEntity> listBookByAuthorName(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author's name must not be blank or whitespace only !!");
        }
        List<BookEntity> list = bookRepository.findByAuthor_NameContainingIgnoreCase(authorName);
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book of author's name " + authorName);
        else return list;
    }

    public List<BookEntity> listBookByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book's name must not be blank or whitespace only !!");
        }
        List<BookEntity> list = bookRepository.findByTitleContainingIgnoreCase(name);
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book by name " + name);
        else return list;
    }

    public List<BookEntity> listBookByISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN must not be blank or whitespace only !!");
        }
        List<BookEntity> list = bookRepository.findByIsbnContainingIgnoreCase(isbn);
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book with ISBN = " + isbn);
        }
        return list;
    }

//    public List<BookEntity> listBookByCategoryName(String CategoryName) {
//        if (CategoryName == null || CategoryName.trim().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book's name must not be blank or whitespace only !!");
//        }
//        List<BookEntity> list = bookRepository.findByCategoryNameContainingIgnoreCase(CategoryName);
//        if (list.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book by name " + CategoryName);
//        else return list;
//    }
}

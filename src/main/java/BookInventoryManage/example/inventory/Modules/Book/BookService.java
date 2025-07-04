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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
        List<CategoryEntity> listCate = categoryService.getListCateByIds(dto.getCategoryIds(), true);

        AuthorEntity author = authorService.getAuthorByID(dto.getAuthorID());

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
        boolean isUpdate = false;
        if (dto.getTitle() != null && !dto.getTitle().trim().isEmpty()) {
            book.setTitle(dto.getTitle());
            isUpdate = true;
        }

        if (dto.getDescription() != null && !dto.getDescription().trim().isEmpty()) {
            book.setDescription(dto.getDescription());
            isUpdate = true;
        }

        if(isUpdate){
            bookRepository.save(book);
        }

        List<CategoryEntity> listCate = categoryService.getListCateByIds(dto.getCategoryIds(), false);
        if(listCate != null && !listCate.isEmpty()){
            bookCategoryRepository.deleteByBook(book);

            List<BookCategoryEntity> bookCategoryEntities = new ArrayList<>();
            for (CategoryEntity category : listCate) {
                bookCategoryEntities.add(new BookCategoryEntity(book, category));
            }
            isUpdate = true;
            bookCategoryRepository.saveAll(bookCategoryEntities);
        }

        if(!isUpdate){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No information provided for update !!");
        }
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

    public List<BookEntity> listBookByAuthorName(String authorName, boolean isThrow) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author's name must not be blank or whitespace only !!");
        }
        List<BookEntity> list = bookRepository.findByAuthorName(authorName);
        if (list.isEmpty() && isThrow == true)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book of author's name " + authorName);
        else if (list.isEmpty() && isThrow == false)
            return null;
        else return list;
    }

    public List<BookEntity> listBookByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book's name must not be blank or whitespace only !!");
        }
        List<BookEntity> list = bookRepository.findByTitle(name);
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book by name " + name);
        else return list;
    }

    public BookEntity listBookByISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN must not be blank or whitespace only !!");
        }
        Optional<BookEntity> b = bookRepository.findByIsbnCus(isbn);
        if (b.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any book with ISBN = " + isbn);
        }
        return b.get();
    }

}

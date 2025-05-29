package BookInventoryManage.example.inventory.Modules.Review;

import BookInventoryManage.example.inventory.Enums.Role;
import BookInventoryManage.example.inventory.Modules.Book.BookService;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.AccountEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ReviewEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.ReviewRepository;
import BookInventoryManage.example.inventory.Modules.Review.DTO.CreateReviewRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookService bookService;

    public void writeReview(CreateReviewRequestDTO dto) {
        SecurityContext context = SecurityContextHolder.getContext();
        AccountEntity acc = (AccountEntity) context.getAuthentication();
        BookEntity book = bookService.getBookById(dto.getBookId());
        ReviewEntity review = new ReviewEntity(dto, book, acc);
        reviewRepository.save(review);
    }

    public ReviewEntity getReviewById(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review ID must not be empty !!");
        }
        Optional<ReviewEntity> OptReview = reviewRepository.findById(Id);
        if (OptReview.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Review by id = " + Id);
        else return OptReview.get();
    }

    public List<ReviewEntity> listAllReviews() {
        List<ReviewEntity> list = reviewRepository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any review !!");
        else return list;
    }

    public List<ReviewEntity> listALlReviewByBookId(Integer bookID) {
        if (bookID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book ID must not be empty !!");
        }
        List<ReviewEntity> listAllReview = listAllReviews();
        List<ReviewEntity> list = new ArrayList<>();
        for (ReviewEntity review : listAllReview) {
            if (review.getBook().getId() == bookID) {
                list.add(review);
            }
        }
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found review by book Id: " + bookID);
        else return list;
    }

    public List<ReviewEntity> listAllReviewByUserId(Integer userId) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID must not be empty !!");
        }
        List<ReviewEntity> listAllReview = listAllReviews();
        List<ReviewEntity> list = new ArrayList<>();
        for (ReviewEntity review : listAllReview) {
            if (review.getUser().getId() == userId) {
                list.add(review);
            }
        }
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found review by user Id: " + userId);
        else return list;
    }

    public void delete_admin(Integer reviewId) {
        ReviewEntity review = getReviewById(reviewId);
        reviewRepository.delete(review);

    }

    public void delete_user(Integer reviewId) {
        SecurityContext context = SecurityContextHolder.getContext();
        AccountEntity currentAcc = (AccountEntity) context.getAuthentication();
        ReviewEntity review = getReviewById(reviewId);
        if (currentAcc.getRole() == Role.ADMIN || (review.getUser().getId().equals(currentAcc.getId()))) {
            reviewRepository.delete(review);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have sufficient permissions to delete !!");
        }
    }


}

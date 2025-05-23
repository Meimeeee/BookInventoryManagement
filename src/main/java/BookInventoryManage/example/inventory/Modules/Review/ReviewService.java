package BookInventoryManage.example.inventory.Modules.Review;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.BookEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.ReviewEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.ReviewRepository;
import BookInventoryManage.example.inventory.Modules.Review.DTO.CreateReviewRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

//    đợi làm sercutiry
//    public void writeReview(CreateReviewRequestDTO dto) {
//        ReviewEntity review = new ReviewEntity(dto);
//        reviewRepository.save(review);
//    }

    public ReviewEntity getReviewById(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review ID must not be empty !!");
        }
        Optional<ReviewEntity> OptReview = reviewRepository.findById(Id);
        if (OptReview.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Review by id = " + Id);
        else return OptReview.get();
    }

    public void deleteReviewByID(Integer Id) {
        ReviewEntity review = getReviewById(Id);
        reviewRepository.delete(review);
    }


}

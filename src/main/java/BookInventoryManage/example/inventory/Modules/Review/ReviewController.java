package BookInventoryManage.example.inventory.Modules.Review;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.ReviewEntity;
import BookInventoryManage.example.inventory.Modules.Review.DTO.CreateReviewRequestDTO;
import BookInventoryManage.example.inventory.Modules.Review.DTO.ReviewResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/")
    private ResponseEntity writeReview(@RequestBody @Valid CreateReviewRequestDTO dto) {
        reviewService.writeReview(dto);
        return new ResponseEntity("Write review successfuly !!", HttpStatus.OK);
    }

    //    reviews of a book
    @GetMapping("/{bookId}")
    private ResponseEntity getReviewByBookId(@PathVariable("bookId") Integer id) {
        List<ReviewEntity> list = reviewService.listALlReviewByBookId(id);
        return new ResponseEntity(ReviewResponseDTO.fromEntities(list), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewID}")
    private ResponseEntity deleteReview_user(@PathVariable("reviewID") Integer Id) {
        reviewService.delete_user(Id);
        return new ResponseEntity("Deleted !!", HttpStatus.OK);
    }

//    admin
    @DeleteMapping("/admin/{reviewID}")
    private ResponseEntity deleteReview_admin(@PathVariable("reviewID") Integer Id) {
        reviewService.delete_admin(Id);
        return new ResponseEntity("Deleted !!", HttpStatus.OK);
    }
}

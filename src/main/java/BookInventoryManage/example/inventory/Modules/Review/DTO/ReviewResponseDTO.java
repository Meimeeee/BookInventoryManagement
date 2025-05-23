package BookInventoryManage.example.inventory.Modules.Review.DTO;

import BookInventoryManage.example.inventory.Modules.Databases.Entities.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    public String content;
    public Integer rate;
    public LocalDateTime createAt;

    public static ReviewResponseDTO fromEntity(ReviewEntity review) {
        return new ReviewResponseDTO(review.getContent(), review.getRate(), review.getCreateAt());
    }

    public static List<ReviewResponseDTO> fromEntities(List<ReviewEntity> entities) {
        List<ReviewResponseDTO> list = new ArrayList<>();
        for (ReviewEntity review : entities) {
            list.add(fromEntity(review));
        }
        return list;
    }

}

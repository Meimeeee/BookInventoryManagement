package BookInventoryManage.example.inventory.Modules.Category;

import BookInventoryManage.example.inventory.Modules.Category.DTO.CategoryResponseDTO;
import BookInventoryManage.example.inventory.Modules.Category.DTO.CreateCategoryRequestDTO;
import BookInventoryManage.example.inventory.Modules.Category.DTO.UpdateCategoryRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity createCategory(@RequestBody @Valid CreateCategoryRequestDTO dto) {
        categoryService.createCategory(dto);
        return new ResponseEntity("Created !!", HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    ResponseEntity updateCategoryByID(@RequestBody @Valid UpdateCategoryRequestDTO dto, @PathVariable("categoryId") Integer Id) {
        categoryService.updateCategory(Id, dto);
        return new ResponseEntity("Updated !!", HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity listAllCategories() {
        List<CategoryEntity> list = categoryService.getListCategories();
        return new ResponseEntity(CategoryResponseDTO.fromEntities(list), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    ResponseEntity deleteCategory(@PathVariable("categoryId") Integer Id) {
        categoryService.deleteCateById(Id);
        return new ResponseEntity("Deleted !!", HttpStatus.OK);
    }
}

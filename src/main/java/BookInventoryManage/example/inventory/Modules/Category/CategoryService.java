package BookInventoryManage.example.inventory.Modules.Category;

import BookInventoryManage.example.inventory.Modules.Category.DTO.CreateCategoryRequestDTO;
import BookInventoryManage.example.inventory.Modules.Category.DTO.UpdateCategoryRequestDTO;
import BookInventoryManage.example.inventory.Modules.Databases.Entities.CategoryEntity;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.BookCategoryRepository;
import BookInventoryManage.example.inventory.Modules.Databases.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    //    add
    public void createCategory(CreateCategoryRequestDTO dto) {
        boolean exist = categoryRepository.existsByName(dto.getName().trim());
        if (exist) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists !!");
        }
        CategoryEntity cate = new CategoryEntity(dto);
        categoryRepository.save(cate);
    }

    //    update
    public void updateCategory(Integer cateID, UpdateCategoryRequestDTO dto) {
        CategoryEntity category = this.getCateById(cateID);
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name must not be blank or whitespace only !!");
        }
        category.setName(dto.getName());

        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category description must not be blank or whitespace only !!");
        }
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);
    }

    //    delete by id
    public void deleteCateById(Integer Id) {
        CategoryEntity cate = this.getCateById(Id);
        bookCategoryRepository.deleteByCategory(cate);
        categoryRepository.delete(cate);
    }

    //    get all lists
    public List<CategoryEntity> getListCategories() {
        List<CategoryEntity> list = categoryRepository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any Category !!");
        else return list;
    }

    //    get cate by id
    public CategoryEntity getCateById(Integer Id) {
        if (Id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category ID list must not be empty.");
        }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(Id);
        if (optionalCategory.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found category with id: " + Id);
        else return optionalCategory.get();
    }

    //    get list cate by list ID
    public List<CategoryEntity> getListCateByIds(List<Integer> cateIds) {
        if (cateIds == null || cateIds.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category ID list must not be empty.");
        }
        List<CategoryEntity> list = new ArrayList<>();
        for (Integer categoryId : cateIds) {
            CategoryEntity cate = getCateById(categoryId);
            list.add(cate);
        }
        return list;
    }


}

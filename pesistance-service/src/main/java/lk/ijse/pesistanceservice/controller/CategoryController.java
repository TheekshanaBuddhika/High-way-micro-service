package lk.ijse.pesistanceservice.controller;

import lk.ijse.pesistanceservice.dto.CategoryDTO;
import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persistence/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseDTO saveOrUpdateCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            if (categoryDTO.getId() == null) {
                return categoryService.saveCategory(categoryDTO);
            } else {
                return categoryService.updateCategory(categoryDTO);
            }
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/dis/{id}")
    public ResponseDTO disableCategory(@PathVariable Long id) {
        try {
            return categoryService.disableCategory(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/en/{id}")
    public ResponseDTO enableCategory(@PathVariable Long id) {
        try {
            return categoryService.enableCategory(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping
    public ResponseDTO getAllCategory() {
        try {
            return categoryService.getAllCategories();
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO getCategory(@PathVariable Long id) {
        try {
            return categoryService.getCategory(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }
}


package lk.ijse.persistence_service.service;

import lk.ijse.persistence_service.dto.CategoryDTO;
import lk.ijse.persistence_service.dto.ResponseDTO;
import lk.ijse.persistence_service.entity.CategoryEntity;
import lk.ijse.persistence_service.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseDTO saveCategory(CategoryDTO categoryDTO) {
        try {
            if (!categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
                categoryRepository.save(CategoryEntity
                        .builder()
                        .categoryName(categoryDTO.getCategoryName().toUpperCase())
                        .createBy(categoryDTO.getCreateBy())
                        .isActive(true)
                        .build());
                return new ResponseDTO("Category Saved Successfully", 200);
            }else {
                return new ResponseDTO("Category Already Exists", 400);
            }
        }catch (Exception e){
            return new ResponseDTO("Failed to Save Category", 500);
        }
    }

    public ResponseDTO updateCategory(CategoryDTO categoryDTO) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findById(categoryDTO.getId()).orElse(null);

            if (categoryEntity != null) {
                if (!categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
                    categoryEntity.setCategoryName(categoryDTO.getCategoryName());
                    categoryEntity.setModifyBy(categoryDTO.getModifyBy());

                    categoryRepository.save(categoryEntity);
                    return new ResponseDTO("Category Updated Successfully", 200);
                }else {
                    return new ResponseDTO("Category Already Exists", 400);
                }
            } else {
                return new ResponseDTO("Category Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Update Category", 500);
        }
    }

    public ResponseDTO enableCategory(Long id) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);

            if (categoryEntity != null) {
                categoryEntity.setActive(true);

                categoryRepository.save(categoryEntity);
                return new ResponseDTO("Category Enable Successfully", 200);
            } else {
                return new ResponseDTO("Category Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Enable Category", 500);
        }
    }

    public ResponseDTO disableCategory(Long id) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);

            if (categoryEntity != null) {
                categoryEntity.setActive(false);

                categoryRepository.save(categoryEntity);
                return new ResponseDTO("Category Disable Successfully", 200);
            } else {
                return new ResponseDTO("Category Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Disable Category", 500);
        }
    }

    public ResponseDTO getAllCategories() {
        try {
            return new ResponseDTO("All Categories", 200, new HashMap<>(Map.of("categories", categoryRepository.findAll().stream().map(categoryEntity -> CategoryDTO
                    .builder()
                    .id(categoryEntity.getId())
                    .categoryName(categoryEntity.getCategoryName())
                    .createBy(categoryEntity.getCreateBy())
                    .modifyBy(categoryEntity.getModifyBy())
                    .isActive(categoryEntity.isActive())
                    .build()).toList())));
        } catch (Exception e) {
            return new ResponseDTO("Failed to Get Categories", 500);
        }
    }

    public ResponseDTO getCategory(Long id) {
        try {
            CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
            if (categoryEntity != null) {
                return new ResponseDTO("Category", 200, new HashMap<>(Map.of("category", CategoryDTO.builder()
                        .id(categoryEntity.getId())
                        .categoryName(categoryEntity.getCategoryName())
                        .createBy(categoryEntity.getCreateBy())
                        .modifyBy(categoryEntity.getModifyBy())
                        .isActive(categoryEntity.isActive())
                        .build())));
            } else {
                return new ResponseDTO("Category Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Get Categories", 500);
        }
    }
}

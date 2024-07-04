package lk.ijse.persistence_service.repository;

import lk.ijse.persistence_service.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    boolean existsByCategoryName(String categoryName);
}

package lk.ijse.pesistanceservice.repository;

import lk.ijse.pesistanceservice.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    boolean existsByCategoryName(String categoryName);
}

package lk.ijse.persistence_service.repository;

import lk.ijse.persistence_service.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>{
    List<TicketEntity> findByStatus(String status);
}

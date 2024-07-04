package lk.ijse.pesistanceservice.repository;

import lk.ijse.pesistanceservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>{
    List<TicketEntity> findByStatus(String status);
}

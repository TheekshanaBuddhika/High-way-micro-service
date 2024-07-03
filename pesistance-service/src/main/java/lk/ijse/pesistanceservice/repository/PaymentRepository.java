package lk.ijse.pesistanceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository extends JpaRepository<PaymentRepository, Long> {
}

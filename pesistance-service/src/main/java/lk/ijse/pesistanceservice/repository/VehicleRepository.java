package lk.ijse.pesistanceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository extends JpaRepository<VehicleRepository, Long> {
}

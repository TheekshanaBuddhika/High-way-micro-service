package lk.ijse.persistence_service.service;

import lk.ijse.persistence_service.dto.ResponseDTO;
import lk.ijse.persistence_service.dto.VehicleDTO;
import lk.ijse.persistence_service.entity.CategoryEntity;
import lk.ijse.persistence_service.entity.VehicleEntity;
import lk.ijse.persistence_service.repository.CategoryRepository;
import lk.ijse.persistence_service.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CategoryRepository categoryRepository;

    public VehicleService(VehicleRepository vehicleRepository, CategoryRepository categoryRepository) {
        this.vehicleRepository = vehicleRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseDTO saveVehicle(VehicleDTO vehicleDTO) {
        try {
            vehicleRepository.save(VehicleEntity
                    .builder()
                    .vehicleNumber(vehicleDTO.getVehicleNumber())
                    .vehicleBrand(vehicleDTO.getVehicleBrand())
                    .vehicleModel(vehicleDTO.getVehicleModel())
                    .categoryEntity(categoryRepository.findById(vehicleDTO.getCategoryId()).get())
                    .createBy(vehicleDTO.getCreateBy())
                    .isActive(true)
                    .build());

            return new ResponseDTO("Vehicle Saved Successfully", 200);
        }catch (Exception e){
            return new ResponseDTO("Failed to Save Vehicle", 500);
        }
    }

    public ResponseDTO updateVehicle(VehicleDTO vehicleDTO) {
        try {
            VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleDTO.getId()).orElse(null);

            if (vehicleEntity != null){
                vehicleEntity.setVehicleNumber(vehicleDTO.getVehicleNumber());
                vehicleEntity.setVehicleBrand(vehicleDTO.getVehicleBrand());
                vehicleEntity.setVehicleModel(vehicleDTO.getVehicleModel());
                vehicleEntity.setCategoryEntity(categoryRepository.findById(vehicleDTO.getCategoryId()).get());
                vehicleEntity.setModifyBy(vehicleDTO.getModifyBy());

                vehicleRepository.save(vehicleEntity);
                return new ResponseDTO("Vehicle Updated Successfully", 200);
            }else {
                return new ResponseDTO("Vehicle Not Found", 404);
            }
        }catch (Exception e){
            return new ResponseDTO("Failed to Save Vehicle", 500);
        }
    }

    public ResponseDTO enableVehicle(Long id) {
        try {
            VehicleEntity vehicleEntity = vehicleRepository.findById(id).orElse(null);

            if (vehicleEntity != null) {
                vehicleEntity.setActive(true);

                vehicleRepository.save(vehicleEntity);
                return new ResponseDTO("Vehicle Enable Successfully", 200);
            } else {
                return new ResponseDTO("Vehicle Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Vehicle to Enable Category", 500);
        }
    }

    public ResponseDTO disableVehicle(Long id) {
        try {
            VehicleEntity vehicleEntity = vehicleRepository.findById(id).orElse(null);

            if (vehicleEntity != null) {
                vehicleEntity.setActive(false);

                vehicleRepository.save(vehicleEntity);
                return new ResponseDTO("Vehicle Disable Successfully", 200);
            } else {
                return new ResponseDTO("Vehicle Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Vehicle to Disable Category", 500);
        }
    }

    public ResponseDTO getAllVehicles() {
        try {
            return new ResponseDTO("All Vehicles", 200, new HashMap<>(Map.of("vehicles", vehicleRepository.findAll().stream().map(vehicleEntity -> VehicleDTO
                    .builder()
                    .id(vehicleEntity.getId())
                    .vehicleNumber(vehicleEntity.getVehicleNumber())
                    .vehicleBrand(vehicleEntity.getVehicleBrand())
                    .vehicleModel(vehicleEntity.getVehicleModel())
                    .categoryId(vehicleEntity.getCategoryEntity().getId())
                    .createBy(vehicleEntity.getCreateBy())
                    .modifyBy(vehicleEntity.getModifyBy())
                    .isActive(vehicleEntity.isActive())
                    .build()).toList())));

        } catch (Exception e) {
            return new ResponseDTO("Failed to Get Vehicles", 500);
        }
    }

    public ResponseDTO getVehicle(Long id) {
        try {
            VehicleEntity vehicleEntity = vehicleRepository.findById(id).orElse(null);
            if (vehicleEntity != null) {
                return new ResponseDTO("Vehicle", 200, new HashMap<>(Map.of("vehicle", VehicleDTO
                        .builder()
                        .id(vehicleEntity.getId())
                        .vehicleNumber(vehicleEntity.getVehicleNumber())
                        .vehicleBrand(vehicleEntity.getVehicleBrand())
                        .vehicleModel(vehicleEntity.getVehicleModel())
                        .categoryId(vehicleEntity.getCategoryEntity().getId())
                        .createBy(vehicleEntity.getCreateBy())
                        .modifyBy(vehicleEntity.getModifyBy())
                        .isActive(vehicleEntity.isActive())
                        .build())));
            } else {
                return new ResponseDTO("Vehicles Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Get Vehicles", 500);
        }
    }
}

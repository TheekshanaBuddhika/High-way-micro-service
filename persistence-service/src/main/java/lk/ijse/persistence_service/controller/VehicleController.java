package lk.ijse.persistence_service.controller;

import lk.ijse.persistence_service.dto.ResponseDTO;
import lk.ijse.persistence_service.dto.VehicleDTO;
import lk.ijse.persistence_service.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persistence/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseDTO saveOrUpdateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            if (vehicleDTO.getId() == null) {
                return vehicleService.saveVehicle(vehicleDTO);
            } else {
                return vehicleService.updateVehicle(vehicleDTO);
            }
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/dis/{id}")
    public ResponseDTO disableVehicle(@PathVariable Long id) {
        try {
            return vehicleService.disableVehicle(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/en/{id}")
    public ResponseDTO enableVehicle(@PathVariable Long id) {
        try {
            return vehicleService.enableVehicle(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping
    public ResponseDTO getAllVehicles() {
        try {
            return vehicleService.getAllVehicles();
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO getVehicle(@PathVariable Long id) {
        try {
            return vehicleService.getVehicle(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }
}

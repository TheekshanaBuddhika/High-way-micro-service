package lk.ijse.vehicle_service.controller;

import lk.ijse.vehicle_service.dto.ResponseDTO;
import lk.ijse.vehicle_service.dto.VehicleDTO;
import lk.ijse.vehicle_service.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseDTO saveOrUpdateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        if (vehicleDTO.getId() == null) {
            return vehicleService.saveVehicle(vehicleDTO);
        } else {
            return vehicleService.updateVehicle(vehicleDTO);
        }
    }

    @PostMapping("/dis/{id}")
    public ResponseDTO disableVehicle(@PathVariable Long id) {
        return vehicleService.disableVehicle(id);
    }

    @PostMapping("/en/{id}")
    public ResponseDTO enableVehicle(@PathVariable Long id) {
        return vehicleService.enableVehicle(id);
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @GetMapping("/all")
    public ResponseDTO getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}

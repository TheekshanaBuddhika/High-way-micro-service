package lk.ijse.vehicle_service.service;

import lk.ijse.vehicle_service.dto.ResponseDTO;
import lk.ijse.vehicle_service.dto.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleService {

    private final RestTemplate restTemplate;

    public VehicleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDTO saveVehicle(VehicleDTO vehicleDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/vehicle", vehicleDTO, ResponseDTO.class);
    }

    public ResponseDTO updateVehicle(VehicleDTO vehicleDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/vehicle", vehicleDTO, ResponseDTO.class);
    }

    public ResponseDTO disableVehicle(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/vehicle/dis/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO enableVehicle(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/vehicle/en/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO getAllVehicles() {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/vehicle", ResponseDTO.class);
    }

    public ResponseDTO getVehicle(Long id) {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/vehicle/" + id, ResponseDTO.class);
    }
}

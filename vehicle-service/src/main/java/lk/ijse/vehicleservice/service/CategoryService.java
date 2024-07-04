package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.CategoryDTO;
import lk.ijse.vehicleservice.dto.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {

    private final RestTemplate restTemplate;

    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDTO saveCategory(CategoryDTO categoryDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/category", categoryDTO, ResponseDTO.class);
    }

    public ResponseDTO updateCategory(CategoryDTO categoryDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/category", categoryDTO, ResponseDTO.class);
    }

    public ResponseDTO disableCategory(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/category/dis/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO enableCategory(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/category/en/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO getAllCategories() {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/category", ResponseDTO.class);
    }

    public ResponseDTO getCategory(Long id) {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/category/" + id, ResponseDTO.class);
    }

}

package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.AuthDTO;
import lk.ijse.userservice.dto.ResponseDTO;
import lk.ijse.userservice.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDTO saveUser(UserDTO userDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/user", userDTO, ResponseDTO.class);
    }

    public ResponseDTO updateUser(UserDTO userDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/user", userDTO, ResponseDTO.class);
    }

    public ResponseDTO disableUser(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/user/dis/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO enableUser(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/user/en/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO getUser(Long id) {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/user/" + id, ResponseDTO.class);
    }

    public ResponseDTO getAllUsers() {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/user", ResponseDTO.class);
    }

    public ResponseDTO changePassword(AuthDTO authDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/user/pwChange", authDTO, ResponseDTO.class);
    }
}

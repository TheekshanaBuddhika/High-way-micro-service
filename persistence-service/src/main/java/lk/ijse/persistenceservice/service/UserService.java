package lk.ijse.pesistanceservice.service;

import lk.ijse.pesistanceservice.dto.AuthDTO;
import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.dto.UserDTO;
import lk.ijse.pesistanceservice.entity.UserEntity;
import lk.ijse.pesistanceservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDTO saveUser(UserDTO userDTO) {
        try {
            if (!userRepository.existsByEmail(userDTO.getEmail())) {
                userRepository.save(UserEntity
                        .builder()
                        .userName(userDTO.getUserName())
                        .email(userDTO.getEmail())
                        .password(userDTO.getPassword())
                        .mobile(userDTO.getMobile())
                        .createBy(userDTO.getCreateBy())
                        .isActive(true)
                        .build());
                return new ResponseDTO("User Saved Successfully", 200);
            } else {
                return new ResponseDTO("Email Already Exists", 400);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Save User", 500);
        }
    }

    public ResponseDTO updateUser(UserDTO userDTO) {
        try {
            UserEntity userEntity = userRepository.findById(userDTO.getId()).orElse(null);

            if (userEntity != null) {
                if (!userRepository.existsByEmail(userDTO.getEmail())) {
                    userEntity.setUserName(userDTO.getUserName());
                    userEntity.setEmail(userDTO.getEmail());
                    userEntity.setMobile(userDTO.getMobile());
                    userEntity.setModifyBy(userDTO.getModifyBy());

                    userRepository.save(userEntity);
                    return new ResponseDTO("User Updated Successfully", 200);
                } else {
                    return new ResponseDTO("Email Already Exists", 400);
                }
            } else {
                return new ResponseDTO("User Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Update User", 500);
        }
    }

    public ResponseDTO enableUser(Long id) {
        try {
            UserEntity userEntity = userRepository.findById(id).orElse(null);

            if (userEntity != null) {
                userEntity.setActive(true);

                userRepository.save(userEntity);
                return new ResponseDTO("User Enable Successfully", 200);
            } else {
                return new ResponseDTO("User Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Enable User", 500);
        }
    }

    public ResponseDTO disableUser(Long id) {
        try {
            UserEntity userEntity = userRepository.findById(id).orElse(null);

            if (userEntity != null) {
                userEntity.setActive(false);

                userRepository.save(userEntity);
                return new ResponseDTO("User Disable Successfully", 200);
            } else {
                return new ResponseDTO("User Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Disable User", 500);
        }
    }

    public ResponseDTO getAllUser() {
        try {
            return new ResponseDTO("All User", 200, new HashMap<>(Map.of("User", userRepository.findAll().stream().map(userEntity -> UserDTO
                    .builder()
                    .id(userEntity.getId())
                    .userName(userEntity.getUserName())
                    .email(userEntity.getEmail())
                    .mobile(userEntity.getMobile())
                    .createBy(userEntity.getCreateBy())
                    .modifyBy(userEntity.getModifyBy())
                    .isActive(userEntity.isActive())
                    .build()).toList())));
        } catch (Exception e) {
            return new ResponseDTO("Failed to Get Users", 500);
        }
    }

    public ResponseDTO getUser(Long id) {
        try {
            UserEntity userEntity = userRepository.findById(id).orElse(null);
            if (userEntity != null) {
                return new ResponseDTO("User", 200, new HashMap<>(Map.of("user", UserDTO.builder()
                        .id(userEntity.getId())
                        .userName(userEntity.getUserName())
                        .email(userEntity.getEmail())
                        .mobile(userEntity.getMobile())
                        .createBy(userEntity.getCreateBy())
                        .modifyBy(userEntity.getModifyBy())
                        .isActive(userEntity.isActive())
                        .build())));
            } else {
                return new ResponseDTO("User Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Get User", 500);
        }
    }

    public ResponseDTO changePassword(AuthDTO authDTO) {
        try {
            UserEntity userEntity = userRepository.findByEmail(authDTO.getEmail()).orElse(null);
            if (userEntity != null) {
                userEntity.setPassword(authDTO.getPassword());
                userRepository.save(userEntity);
                return new ResponseDTO("Password Changed Successfully", 200);
            } else {
                return new ResponseDTO("User Not Found", 404);
            }
        } catch (Exception e) {
            return new ResponseDTO("Failed to Change Password", 500);
        }
    }
}


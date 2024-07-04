package lk.ijse.pesistanceservice.controller;

import lk.ijse.pesistanceservice.dto.AuthDTO;
import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.dto.UserDTO;
import lk.ijse.pesistanceservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persistence/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseDTO saveOrUpdateUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getId() == null) {
                return userService.saveUser(userDTO);
            } else {
                return userService.updateUser(userDTO);
            }
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/pwChange")
    public ResponseDTO changePassword(@RequestBody AuthDTO authDTO) {
        try {
            return userService.changePassword(authDTO);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/dis/{id}")
    public ResponseDTO disableUser(@PathVariable Long id) {
        try {
            return userService.disableUser(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/en/{id}")
    public ResponseDTO enableUser(@PathVariable Long id) {
        try {
            return userService.enableUser(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping
    public ResponseDTO getAllUsers() {
        try {
            return userService.getAllUser();
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO getUser(@PathVariable Long id) {
        try {
            return userService.getUser(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }
}

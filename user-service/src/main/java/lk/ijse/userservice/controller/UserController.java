package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.AuthDTO;
import lk.ijse.userservice.dto.ResponseDTO;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseDTO saveOrUpdateUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            return userService.saveUser(userDTO);
        } else {
            return userService.updateUser(userDTO);
        }
    }

    @PostMapping("/changePw")
    public ResponseDTO disableUser(@RequestBody AuthDTO authDTO) {
        return userService.changePassword(authDTO);
    }

    @PostMapping("/dis/{id}")
    public ResponseDTO disableUser(@PathVariable Long id) {
        return userService.disableUser(id);
    }

    @PostMapping("/en/{id}")
    public ResponseDTO enableUser(@PathVariable Long id) {
        return userService.enableUser(id);
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public ResponseDTO getAllCategories() {
        return userService.getAllUsers();
    }

}

package lk.ijse.userservice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private String createBy;
    private String modifyBy;
    private boolean isActive;
}

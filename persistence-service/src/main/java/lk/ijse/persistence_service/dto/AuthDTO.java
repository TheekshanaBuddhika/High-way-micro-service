package lk.ijse.persistence_service.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO implements Serializable {
    private String email;
    private String password;
}

package lk.ijse.pesistanceservice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO implements Serializable {
    private Long id;
    private String vehicleNumber;
    private String vehicleBrand;
    private String vehicleModel;
    private Long categoryId;
    private String createBy;
    private String modifyBy;
    private boolean isActive;
}

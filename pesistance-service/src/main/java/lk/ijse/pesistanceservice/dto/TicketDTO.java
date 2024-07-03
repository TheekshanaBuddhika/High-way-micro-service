package lk.ijse.pesistanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketDTO implements Serializable {
    private Long id;
    private Date issueDate;
    private String issueLocation;
    private String issueTime;
    private Date arrivalDate;
    private String arrivalLocation;
    private String arrivalTime;
    private String status;
    private Long userId;
    private Long paymentId;
    private Long vehicleId;
    private String createBy;
    private String modifyBy;
    private boolean isActive;
}

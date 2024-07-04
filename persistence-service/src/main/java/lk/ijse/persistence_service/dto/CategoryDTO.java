package lk.ijse.persistence_service.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Long id;
    private String categoryName;
    private String createBy;
    private String modifyBy;
    private boolean isActive;
}

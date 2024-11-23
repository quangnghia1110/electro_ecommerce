package hcmute.projectBackend2024.dto.authentication;

import lombok.Data;

@Data
public class RoleRequest {
    private String code;
    private String name;
    private Integer status;
}

package hcmute.projectBackend2024.dto.inventory;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.dto.address.AddressRequest;

@Data
public class WarehouseRequest {
    private String code;
    private String name;
    @Nullable
    private AddressRequest address;
    private Integer status;
}

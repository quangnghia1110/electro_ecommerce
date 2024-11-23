package hcmute.projectBackend2024.dto.inventory;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.dto.address.AddressResponse;

import java.time.Instant;

@Data
public class WarehouseResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String code;
    private String name;
    @Nullable
    private AddressResponse address;
    private Integer status;
}

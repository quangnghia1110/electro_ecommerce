package hcmute.projectBackend2024.dto.inventory;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.dto.address.AddressRequest;

@Data
public class DestinationRequest {
    @Nullable
    private String contactFullname;
    @Nullable
    private String contactEmail;
    @Nullable
    private String contactPhone;
    private AddressRequest address;
    private Integer status;
}

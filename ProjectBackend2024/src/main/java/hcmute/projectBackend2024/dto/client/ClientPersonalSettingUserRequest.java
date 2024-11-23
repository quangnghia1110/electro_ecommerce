package hcmute.projectBackend2024.dto.client;

import hcmute.projectBackend2024.dto.address.AddressRequest;
import lombok.Data;

@Data
public class ClientPersonalSettingUserRequest {
    private String username;
    private String fullname;
    private String gender;
    private AddressRequest address;
}

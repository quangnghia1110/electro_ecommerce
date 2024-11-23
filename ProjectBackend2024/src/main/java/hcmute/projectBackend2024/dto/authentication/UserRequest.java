package hcmute.projectBackend2024.dto.authentication;

import lombok.Data;

import java.util.Set;

import hcmute.projectBackend2024.dto.address.AddressRequest;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private AddressRequest address;
    private String avatar;
    private Integer status;
    private Set<Role_UserRequest> roles;
}

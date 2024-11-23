package hcmute.projectBackend2024.service.auth;

import hcmute.projectBackend2024.dto.authentication.RegistrationRequest;
import hcmute.projectBackend2024.dto.authentication.ResetPasswordRequest;
import hcmute.projectBackend2024.dto.authentication.UserRequest;

public interface VerificationService {

    Long generateTokenVerify(UserRequest userRequest);

    void resendRegistrationToken(Long userId);

    void confirmRegistration(RegistrationRequest registration);

    void changeRegistrationEmail(Long userId, String emailUpdate);

    void forgetPassword(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

}

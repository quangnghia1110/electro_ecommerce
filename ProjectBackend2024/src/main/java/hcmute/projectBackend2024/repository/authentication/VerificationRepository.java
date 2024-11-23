package hcmute.projectBackend2024.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.authentication.Verification;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long>, JpaSpecificationExecutor<Verification> {

    Optional<Verification> findByUserId(Long userId);

}

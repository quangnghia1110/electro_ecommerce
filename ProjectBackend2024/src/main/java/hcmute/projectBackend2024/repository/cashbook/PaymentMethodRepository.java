package hcmute.projectBackend2024.repository.cashbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.cashbook.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>, JpaSpecificationExecutor<PaymentMethod> {

    List<PaymentMethod> findAllByStatus(Integer status);

}

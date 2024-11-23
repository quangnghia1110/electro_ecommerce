package hcmute.projectBackend2024.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.product.Guarantee;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long>, JpaSpecificationExecutor<Guarantee> {
}
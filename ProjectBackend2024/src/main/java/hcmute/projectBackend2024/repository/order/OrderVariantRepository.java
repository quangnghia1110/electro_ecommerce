package hcmute.projectBackend2024.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.variant.OrderVariant;
import hcmute.projectBackend2024.entity.variant.OrderVariant.OrderVariantKey;

public interface OrderVariantRepository extends JpaRepository<OrderVariant, OrderVariantKey>,
        JpaSpecificationExecutor<OrderVariant> {}

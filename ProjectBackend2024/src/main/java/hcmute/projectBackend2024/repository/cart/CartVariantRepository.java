package hcmute.projectBackend2024.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.variant.CartVariant;
import hcmute.projectBackend2024.entity.variant.CartVariant.CartVariantKey;

public interface CartVariantRepository extends JpaRepository<CartVariant, CartVariantKey>,
        JpaSpecificationExecutor<CartVariant> {}

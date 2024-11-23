package hcmute.projectBackend2024.dto.inventory;

import lombok.Data;
import org.springframework.lang.Nullable;

import hcmute.projectBackend2024.dto.product.BrandResponse;
import hcmute.projectBackend2024.dto.product.SupplierResponse;

import java.time.Instant;
import java.util.List;

@Data
public class ProductInventoryResponse {
    private ProductInventoryResponse.ProductResponse product;
    private Integer inventory;
    private Integer waitingForDelivery;
    private Integer canBeSold;
    private Integer areComing;

    @Data
    public static class ProductResponse {
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String name;
        private String code;
        private String slug;
        @Nullable
        private BrandResponse brand;
        @Nullable
        private SupplierResponse supplier;
    }
}

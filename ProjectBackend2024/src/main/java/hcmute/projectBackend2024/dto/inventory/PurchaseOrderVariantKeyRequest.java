package hcmute.projectBackend2024.dto.inventory;

import lombok.Data;

@Data
public class PurchaseOrderVariantKeyRequest {
    private Long purchaseOrderId;
    private Long variantId;
}

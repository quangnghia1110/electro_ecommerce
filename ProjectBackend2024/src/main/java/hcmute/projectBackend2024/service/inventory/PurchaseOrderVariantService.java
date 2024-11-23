package hcmute.projectBackend2024.service.inventory;

import hcmute.projectBackend2024.dto.inventory.PurchaseOrderVariantRequest;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderVariantResponse;
import hcmute.projectBackend2024.entity.variant.PurchaseOrderVariant.PurchaseOrderVariantKey;
import hcmute.projectBackend2024.generic.CrudService;

public interface PurchaseOrderVariantService extends CrudService<PurchaseOrderVariantKey, PurchaseOrderVariantRequest,
        PurchaseOrderVariantResponse> {}

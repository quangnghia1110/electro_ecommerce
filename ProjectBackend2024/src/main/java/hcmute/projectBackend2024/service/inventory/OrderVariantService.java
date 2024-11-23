package hcmute.projectBackend2024.service.inventory;

import hcmute.projectBackend2024.dto.order.OrderVariantRequest;
import hcmute.projectBackend2024.dto.order.OrderVariantResponse;
import hcmute.projectBackend2024.entity.variant.OrderVariant.OrderVariantKey;
import hcmute.projectBackend2024.generic.CrudService;

public interface OrderVariantService extends CrudService<OrderVariantKey, OrderVariantRequest, OrderVariantResponse> {}

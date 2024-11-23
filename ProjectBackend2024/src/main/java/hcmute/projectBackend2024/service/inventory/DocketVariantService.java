package hcmute.projectBackend2024.service.inventory;

import hcmute.projectBackend2024.dto.inventory.DocketVariantRequest;
import hcmute.projectBackend2024.dto.inventory.DocketVariantResponse;
import hcmute.projectBackend2024.entity.variant.DocketVariant.DocketVariantKey;
import hcmute.projectBackend2024.generic.CrudService;

public interface DocketVariantService extends CrudService<DocketVariantKey, DocketVariantRequest, DocketVariantResponse> {}

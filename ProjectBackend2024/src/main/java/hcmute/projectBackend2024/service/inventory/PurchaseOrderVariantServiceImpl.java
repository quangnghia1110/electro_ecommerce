package hcmute.projectBackend2024.service.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.constant.SearchFields;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderVariantRequest;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderVariantResponse;
import hcmute.projectBackend2024.entity.variant.PurchaseOrderVariant.PurchaseOrderVariantKey;
import hcmute.projectBackend2024.mapper.inventory.PurchaseOrderVariantMapper;
import hcmute.projectBackend2024.repository.inventory.PurchaseOrderVariantRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchaseOrderVariantServiceImpl implements PurchaseOrderVariantService {

    private final PurchaseOrderVariantRepository purchaseOrderVariantRepository;
    private final PurchaseOrderVariantMapper purchaseOrderVariantMapper;

    @Override
    public ListResponse<PurchaseOrderVariantResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.PURCHASE_ORDER_VARIANT, purchaseOrderVariantRepository, purchaseOrderVariantMapper);
    }

    @Override
    public PurchaseOrderVariantResponse findById(PurchaseOrderVariantKey id) {
        return defaultFindById(id, purchaseOrderVariantRepository, purchaseOrderVariantMapper, ResourceName.PURCHASE_ORDER_VARIANT);
    }

    @Override
    public PurchaseOrderVariantResponse save(PurchaseOrderVariantRequest request) {
        return defaultSave(request, purchaseOrderVariantRepository, purchaseOrderVariantMapper);
    }

    @Override
    public PurchaseOrderVariantResponse save(PurchaseOrderVariantKey id, PurchaseOrderVariantRequest request) {
        return defaultSave(id, request, purchaseOrderVariantRepository, purchaseOrderVariantMapper, ResourceName.PURCHASE_ORDER_VARIANT);
    }

    @Override
    public void delete(PurchaseOrderVariantKey id) {
        purchaseOrderVariantRepository.deleteById(id);
    }

    @Override
    public void delete(List<PurchaseOrderVariantKey> ids) {
        purchaseOrderVariantRepository.deleteAllById(ids);
    }
}

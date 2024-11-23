package hcmute.projectBackend2024.service.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.constant.SearchFields;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.order.OrderVariantRequest;
import hcmute.projectBackend2024.dto.order.OrderVariantResponse;
import hcmute.projectBackend2024.entity.variant.OrderVariant.OrderVariantKey;
import hcmute.projectBackend2024.mapper.order.OrderVariantMapper;
import hcmute.projectBackend2024.repository.order.OrderVariantRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderVariantServiceImpl implements OrderVariantService {

    private OrderVariantRepository orderVariantRepository;

    private OrderVariantMapper orderVariantMapper;

    @Override
    public ListResponse<OrderVariantResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.ORDER_VARIANT, orderVariantRepository, orderVariantMapper);
    }

    @Override
    public OrderVariantResponse findById(OrderVariantKey id) {
        return defaultFindById(id, orderVariantRepository, orderVariantMapper, ResourceName.ORDER_VARIANT);
    }

    @Override
    public OrderVariantResponse save(OrderVariantRequest request) {
        return defaultSave(request, orderVariantRepository, orderVariantMapper);
    }

    @Override
    public OrderVariantResponse save(OrderVariantKey id, OrderVariantRequest request) {
        return defaultSave(id, request, orderVariantRepository, orderVariantMapper, ResourceName.ORDER_VARIANT);
    }

    @Override
    public void delete(OrderVariantKey id) {
        orderVariantRepository.deleteById(id);
    }

    @Override
    public void delete(List<OrderVariantKey> ids) {
        orderVariantRepository.deleteAllById(ids);
    }

}

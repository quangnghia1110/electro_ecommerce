package hcmute.projectBackend2024.mapper.inventory;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.PurchaseOrderRequest;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderResponse;
import hcmute.projectBackend2024.entity.inventory.PurchaseOrder;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.mapper.product.SupplierMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MapperUtils.class, SupplierMapper.class, DestinationMapper.class, PurchaseOrderVariantMapper.class})
public interface PurchaseOrderMapper extends GenericMapper<PurchaseOrder, PurchaseOrderRequest, PurchaseOrderResponse> {

    @Override
    @BeanMapping(qualifiedByName = "attachPurchaseOrder")
    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "destinationId", target = "destination")
    PurchaseOrder requestToEntity(PurchaseOrderRequest request);

    @Override
    @BeanMapping(qualifiedByName = "attachPurchaseOrder")
    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "destinationId", target = "destination")
    PurchaseOrder partialUpdate(@MappingTarget PurchaseOrder entity, PurchaseOrderRequest request);

}

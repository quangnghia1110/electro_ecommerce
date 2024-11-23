package hcmute.projectBackend2024.mapper.inventory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.PurchaseOrderVariantRequest;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderVariantResponse;
import hcmute.projectBackend2024.entity.variant.PurchaseOrderVariant;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperUtils.class)
public interface PurchaseOrderVariantMapper extends GenericMapper<PurchaseOrderVariant, PurchaseOrderVariantRequest,
        PurchaseOrderVariantResponse> {

    @Override
    @Mapping(source = "variantId", target = "variant")
    PurchaseOrderVariant requestToEntity(PurchaseOrderVariantRequest request);

    @Override
    @Mapping(source = "variantId", target = "variant")
    PurchaseOrderVariant partialUpdate(@MappingTarget PurchaseOrderVariant entity, PurchaseOrderVariantRequest request);

}

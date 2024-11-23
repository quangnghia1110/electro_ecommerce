package hcmute.projectBackend2024.mapper.inventory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import hcmute.projectBackend2024.dto.inventory.DocketVariantRequest;
import hcmute.projectBackend2024.dto.inventory.DocketVariantResponse;
import hcmute.projectBackend2024.entity.variant.DocketVariant;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DocketReasonMapper.class, WarehouseMapper.class, MapperUtils.class})
public interface DocketVariantMapper extends GenericMapper<DocketVariant, DocketVariantRequest, DocketVariantResponse> {

    @Override
    @Mapping(source = "variantId", target = "variant")
    DocketVariant requestToEntity(DocketVariantRequest request);

    @Override
    @Mapping(source = "variantId", target = "variant")
    DocketVariant partialUpdate(@MappingTarget DocketVariant entity, DocketVariantRequest request);

  
}

package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.VariantInventoryResponse;
import hcmute.projectBackend2024.mapper.inventory.DocketVariantMapper;
import hcmute.projectBackend2024.dto.inventory.projection.VariantInventoryRequest;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = DocketVariantMapper.class)
public interface VariantInventoryMapper {

    VariantInventoryResponse toResponse(VariantInventoryRequest variantInventory);

    List<VariantInventoryResponse> toResponse(List<VariantInventoryRequest> variantInventory);

}

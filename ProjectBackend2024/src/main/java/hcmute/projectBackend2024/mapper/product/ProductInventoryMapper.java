package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.ProductInventoryResponse;
import hcmute.projectBackend2024.dto.inventory.projection.ProductInventoryRequest;
import hcmute.projectBackend2024.mapper.inventory.DocketVariantMapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DocketVariantMapper.class, BrandMapper.class, SupplierMapper.class})
public interface ProductInventoryMapper {

    ProductInventoryResponse toResponse(ProductInventoryRequest productInventory);

    List<ProductInventoryResponse> toResponse(List<ProductInventoryRequest> productInventory);

}

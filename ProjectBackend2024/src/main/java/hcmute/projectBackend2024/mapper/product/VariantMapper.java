package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.VariantRequest;
import hcmute.projectBackend2024.dto.product.VariantResponse;
import hcmute.projectBackend2024.entity.variant.Variant;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VariantMapper extends GenericMapper<Variant, VariantRequest, VariantResponse> {
}

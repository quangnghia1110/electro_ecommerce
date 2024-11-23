package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.SpecificationRequest;
import hcmute.projectBackend2024.dto.product.SpecificationResponse;
import hcmute.projectBackend2024.entity.product.Specification;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecificationMapper extends GenericMapper<Specification, SpecificationRequest, SpecificationResponse> {
}

package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.PropertyRequest;
import hcmute.projectBackend2024.dto.product.PropertyResponse;
import hcmute.projectBackend2024.entity.product.Property;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyMapper extends GenericMapper<Property, PropertyRequest, PropertyResponse> {
}

package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.UnitRequest;
import hcmute.projectBackend2024.dto.product.UnitResponse;
import hcmute.projectBackend2024.entity.product.Unit;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnitMapper extends GenericMapper<Unit, UnitRequest, UnitResponse> {
}

package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.GuaranteeRequest;
import hcmute.projectBackend2024.dto.product.GuaranteeResponse;
import hcmute.projectBackend2024.entity.product.Guarantee;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GuaranteeMapper extends GenericMapper<Guarantee, GuaranteeRequest, GuaranteeResponse> {
}

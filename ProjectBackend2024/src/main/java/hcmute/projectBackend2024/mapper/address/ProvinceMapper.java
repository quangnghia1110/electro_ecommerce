package hcmute.projectBackend2024.mapper.address;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.address.ProvinceRequest;
import hcmute.projectBackend2024.dto.address.ProvinceResponse;
import hcmute.projectBackend2024.entity.address.Province;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProvinceMapper extends GenericMapper<Province, ProvinceRequest, ProvinceResponse> {
}

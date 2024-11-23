package hcmute.projectBackend2024.mapper.address;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.address.WardRequest;
import hcmute.projectBackend2024.dto.address.WardResponse;
import hcmute.projectBackend2024.entity.address.Ward;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapperUtils.class)
public interface WardMapper extends GenericMapper<Ward, WardRequest, WardResponse> {

    @Override
    @Mapping(source = "districtId", target = "district")
    Ward requestToEntity(WardRequest request);

    @Override
    @Mapping(source = "districtId", target = "district")
    Ward partialUpdate(@MappingTarget Ward entity, WardRequest request);

}

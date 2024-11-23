package hcmute.projectBackend2024.mapper.inventory;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.DestinationRequest;
import hcmute.projectBackend2024.dto.inventory.DestinationResponse;
import hcmute.projectBackend2024.entity.inventory.Destination;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.mapper.address.AddressMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AddressMapper.class)
public interface DestinationMapper extends GenericMapper<Destination, DestinationRequest, DestinationResponse> {}

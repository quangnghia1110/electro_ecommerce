package hcmute.projectBackend2024.mapper.inventory;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.WarehouseRequest;
import hcmute.projectBackend2024.dto.inventory.WarehouseResponse;
import hcmute.projectBackend2024.entity.inventory.Warehouse;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.mapper.address.AddressMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AddressMapper.class)
public interface WarehouseMapper extends GenericMapper<Warehouse, WarehouseRequest, WarehouseResponse> {}

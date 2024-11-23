package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.SupplierRequest;
import hcmute.projectBackend2024.dto.product.SupplierResponse;
import hcmute.projectBackend2024.entity.product.Supplier;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.mapper.address.AddressMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AddressMapper.class)
public interface SupplierMapper extends GenericMapper<Supplier, SupplierRequest, SupplierResponse> {
}

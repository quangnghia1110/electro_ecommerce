package hcmute.projectBackend2024.mapper.authentication;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.authentication.RoleRequest;
import hcmute.projectBackend2024.dto.authentication.RoleResponse;
import hcmute.projectBackend2024.entity.authentication.Role;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends GenericMapper<Role, RoleRequest, RoleResponse> {
}

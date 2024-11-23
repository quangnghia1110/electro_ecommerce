package hcmute.projectBackend2024.mapper.authentication;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.authentication.UserRequest;
import hcmute.projectBackend2024.dto.authentication.UserResponse;
import hcmute.projectBackend2024.dto.client.ClientEmailSettingUserRequest;
import hcmute.projectBackend2024.dto.client.ClientPersonalSettingUserRequest;
import hcmute.projectBackend2024.dto.client.ClientPhoneSettingUserRequest;
import hcmute.projectBackend2024.entity.authentication.User;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.mapper.address.AddressMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MapperUtils.class, AddressMapper.class})
public interface UserMapper extends GenericMapper<User, UserRequest, UserResponse> {

    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(@MappingTarget User entity, ClientPersonalSettingUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(@MappingTarget User entity, ClientPhoneSettingUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(@MappingTarget User entity, ClientEmailSettingUserRequest request);

}

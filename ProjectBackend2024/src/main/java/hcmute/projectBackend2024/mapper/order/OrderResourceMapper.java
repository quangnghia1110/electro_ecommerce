package hcmute.projectBackend2024.mapper.order;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.order.OrderResourceRequest;
import hcmute.projectBackend2024.dto.order.OrderResourceResponse;
import hcmute.projectBackend2024.entity.order.OrderResource;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MapperUtils.class})
public interface OrderResourceMapper extends GenericMapper<OrderResource, OrderResourceRequest, OrderResourceResponse> {

    @Override
    OrderResource requestToEntity(OrderResourceRequest request);

    @Override
    OrderResource partialUpdate(@MappingTarget OrderResource entity, OrderResourceRequest request);

}

package hcmute.projectBackend2024.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.order.OrderCancellationReasonRequest;
import hcmute.projectBackend2024.dto.order.OrderCancellationReasonResponse;
import hcmute.projectBackend2024.entity.order.OrderCancellationReason;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderCancellationReasonMapper extends GenericMapper<OrderCancellationReason, OrderCancellationReasonRequest,
        OrderCancellationReasonResponse> {}

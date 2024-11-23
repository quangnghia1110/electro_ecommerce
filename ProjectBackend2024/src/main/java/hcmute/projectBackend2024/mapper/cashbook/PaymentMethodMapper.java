package hcmute.projectBackend2024.mapper.cashbook;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.cashbook.ClientPaymentMethodResponse;
import hcmute.projectBackend2024.dto.cashbook.PaymentMethodRequest;
import hcmute.projectBackend2024.dto.cashbook.PaymentMethodResponse;
import hcmute.projectBackend2024.entity.cashbook.PaymentMethod;
import hcmute.projectBackend2024.generic.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodMapper extends GenericMapper<PaymentMethod, PaymentMethodRequest, PaymentMethodResponse> {

    @Mapping(source = "id", target = "paymentMethodId")
    @Mapping(source = "name", target = "paymentMethodName")
    @Mapping(source = "code", target = "paymentMethodCode")
    ClientPaymentMethodResponse entityToClientResponse(PaymentMethod entity);

    List<ClientPaymentMethodResponse> entityToClientResponse(List<PaymentMethod> entities);

}

package hcmute.projectBackend2024.controller.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.dto.CollectionWrapper;
import hcmute.projectBackend2024.dto.cashbook.ClientPaymentMethodResponse;
import hcmute.projectBackend2024.mapper.cashbook.PaymentMethodMapper;
import hcmute.projectBackend2024.repository.cashbook.PaymentMethodRepository;

import java.util.List;

@RestController
@RequestMapping("/client-api/payment-methods")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ClientPaymentMethodController {

    private PaymentMethodRepository paymentMethodRepository;
    private PaymentMethodMapper paymentMethodMapper;

    @GetMapping
    public ResponseEntity<CollectionWrapper<ClientPaymentMethodResponse>> getAllPaymentMethods() {
        List<ClientPaymentMethodResponse> clientPaymentMethodResponses = paymentMethodMapper
                .entityToClientResponse(paymentMethodRepository.findAllByStatus(1));
        return ResponseEntity.status(HttpStatus.OK).body(CollectionWrapper.of(clientPaymentMethodResponses));
    }

}

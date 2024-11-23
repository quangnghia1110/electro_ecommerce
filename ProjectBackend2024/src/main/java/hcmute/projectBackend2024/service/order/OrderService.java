package hcmute.projectBackend2024.service.order;

import hcmute.projectBackend2024.dto.client.ClientConfirmedOrderResponse;
import hcmute.projectBackend2024.dto.client.ClientSimpleOrderRequest;

public interface OrderService {

    void cancelOrder(String code);

    ClientConfirmedOrderResponse createClientOrder(ClientSimpleOrderRequest request);

    void captureTransactionPaypal(String paypalOrderId, String payerId);

}

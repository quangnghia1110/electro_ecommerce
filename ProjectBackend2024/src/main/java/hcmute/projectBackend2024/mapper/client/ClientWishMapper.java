package hcmute.projectBackend2024.mapper.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import hcmute.projectBackend2024.dto.client.ClientWishRequest;
import hcmute.projectBackend2024.dto.client.ClientWishResponse;
import hcmute.projectBackend2024.entity.client.Wish;
import hcmute.projectBackend2024.repository.authentication.UserRepository;
import hcmute.projectBackend2024.repository.product.ProductRepository;

import java.util.Collections;

@Component
@AllArgsConstructor
public class ClientWishMapper {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ClientProductMapper clientProductMapper;

    public Wish requestToEntity(ClientWishRequest request) {
        Wish entity = new Wish();
        entity.setUser(userRepository.getById(request.getUserId()));
        entity.setProduct(productRepository.getById(request.getProductId()));
        return entity;
    }

    public ClientWishResponse entityToResponse(Wish entity) {
        ClientWishResponse response = new ClientWishResponse();
        response.setWishId(entity.getId());
        response.setWishCreatedAt(entity.getCreatedAt());
        // TODO: Triển khai `saleable` cho productResponse ở đây
        response.setWishProduct(clientProductMapper.entityToListedResponse(entity.getProduct(), Collections.emptyList()));
        return response;
    }

}

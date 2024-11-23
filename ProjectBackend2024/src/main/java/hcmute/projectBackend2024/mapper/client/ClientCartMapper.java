package hcmute.projectBackend2024.mapper.client;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import hcmute.projectBackend2024.dto.client.ClientCartRequest;
import hcmute.projectBackend2024.dto.client.ClientCartResponse;
import hcmute.projectBackend2024.dto.client.ClientCartVariantRequest;
import hcmute.projectBackend2024.dto.client.ClientCartVariantResponse;
import hcmute.projectBackend2024.dto.client.UpdateQuantityType;
import hcmute.projectBackend2024.entity.cart.Cart;
import hcmute.projectBackend2024.entity.general.Image;
import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.entity.variant.CartVariant;
import hcmute.projectBackend2024.entity.variant.CartVariant.CartVariantKey;
import hcmute.projectBackend2024.entity.variant.Variant;
import hcmute.projectBackend2024.repository.authentication.UserRepository;
import hcmute.projectBackend2024.repository.inventory.DocketVariantRepository;
import hcmute.projectBackend2024.repository.product.VariantRepository;
import hcmute.projectBackend2024.utils.InventoryUtils;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientCartMapper {

    private UserRepository userRepository;
    private VariantRepository variantRepository;
    private DocketVariantRepository docketVariantRepository;

    public Cart requestToEntity(ClientCartRequest request) {
        var entity = new Cart();
        entity.setUser(userRepository.getById(request.getUserId()));
        entity.setCartVariants(request.getCartItems().stream().map(this::requestToEntity).collect(Collectors.toSet()));
        entity.setStatus(request.getStatus());
        attach(entity);
        return entity;
    }

    public Cart partialUpdate(Cart entity, ClientCartRequest request) {
        List<Long> currentVariantIds = entity.getCartVariants().stream()
                .map(CartVariant::getCartVariantKey)
                .map(CartVariantKey::getVariantId)
                .collect(Collectors.toList());
        Set<CartVariant> newCartVariants = new HashSet<>();

        // (1) Cập nhật các cartVariant đang có trong cart
        for (CartVariant cartVariant : entity.getCartVariants()) {
            for (ClientCartVariantRequest clientCartVariantRequest : request.getCartItems()) {
                if (Objects.equals(cartVariant.getCartVariantKey().getVariantId(), clientCartVariantRequest.getVariantId())) {
                    if (request.getUpdateQuantityType() == UpdateQuantityType.OVERRIDE) {
                        cartVariant.setQuantity(clientCartVariantRequest.getQuantity());
                    } else {
                        cartVariant.setQuantity(cartVariant.getQuantity() + clientCartVariantRequest.getQuantity());
                    }
                    break;
                }
            }
        }

        // (2) Thêm những cartVariant mới từ request
        for (ClientCartVariantRequest clientCartVariantRequest : request.getCartItems()) {
            if (!currentVariantIds.contains(clientCartVariantRequest.getVariantId())) {
                newCartVariants.add(requestToEntity(clientCartVariantRequest));
            }
        }

        entity.getCartVariants().addAll(newCartVariants);
        entity.setStatus(request.getStatus());
        attach(entity);
        return entity;
    }

    private CartVariant requestToEntity(ClientCartVariantRequest request) {
        var entity = new CartVariant();
        entity.setVariant(variantRepository.getById(request.getVariantId()));
        entity.setQuantity(request.getQuantity());
        return entity;
    }

    private void attach(Cart cart) {
        cart.getCartVariants().forEach(cartVariant -> {
            cartVariant.setCartVariantKey(new CartVariantKey(cart.getId(), cartVariant.getVariant().getId()));
            cartVariant.setCart(cart);
        });
    }

    public ClientCartResponse entityToResponse(Cart entity) {
        var response = new ClientCartResponse();
        response.setCartId(entity.getId());
        // Reference: https://stackoverflow.com/a/51331393
        response.setCartItems(entity.getCartVariants().stream()
                .sorted(Comparator.comparing(CartVariant::getCreatedAt))
                .map(this::entityToResponse)
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return response;
    }

    private ClientCartVariantResponse.ClientVariantResponse.ClientProductResponse entityToResponse(Product entity) {
        var response = new ClientCartVariantResponse.ClientVariantResponse.ClientProductResponse();
        response.setProductId(entity.getId());
        response.setProductName(entity.getName());
        response.setProductSlug(entity.getSlug());
        response.setProductThumbnail(entity.getImages().stream().filter(Image::getIsThumbnail).findAny().map(Image::getPath).orElse(null));
        
        return response;
    }

    private ClientCartVariantResponse.ClientVariantResponse entityToResponse(Variant entity) {
        var response = new ClientCartVariantResponse.ClientVariantResponse();
        response.setVariantId(entity.getId());
        response.setVariantProduct(entityToResponse(entity.getProduct()));
        response.setVariantPrice(entity.getPrice());
        response.setVariantProperties(entity.getProperties());
        response.setVariantInventory(InventoryUtils
                .calculateInventoryIndices(docketVariantRepository.findByVariantId(entity.getId()))
                .get("canBeSold"));
        return response;
    }

    private ClientCartVariantResponse entityToResponse(CartVariant entity) {
        var response = new ClientCartVariantResponse();
        response.setCartItemVariant(entityToResponse(entity.getVariant()));
        response.setCartItemQuantity(entity.getQuantity());
        return response;
    }

}

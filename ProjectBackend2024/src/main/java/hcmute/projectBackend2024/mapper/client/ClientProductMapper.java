package hcmute.projectBackend2024.mapper.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import hcmute.projectBackend2024.dto.client.ClientListedProductResponse;
import hcmute.projectBackend2024.dto.client.ClientProductResponse;
import hcmute.projectBackend2024.dto.inventory.projection.SimpleProductInventoryRequest;
import hcmute.projectBackend2024.entity.general.Image;
import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.entity.variant.Variant;
import hcmute.projectBackend2024.mapper.general.ImageMapper;
import hcmute.projectBackend2024.repository.inventory.DocketVariantRepository;
import hcmute.projectBackend2024.utils.InventoryUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientProductMapper {

    private ImageMapper imageMapper;
    private ClientCategoryMapper clientCategoryMapper;
    private DocketVariantRepository docketVariantRepository;

    public ClientListedProductResponse entityToListedResponse(Product product, List<SimpleProductInventoryRequest> productInventories) {
        ClientListedProductResponse clientListedProductResponse = new ClientListedProductResponse();

        clientListedProductResponse
                .setProductId(product.getId())
                .setProductName(product.getName())
                .setProductSlug(product.getSlug())
                .setProductThumbnail(product.getImages().stream()
                        .filter(Image::getIsThumbnail)
                        .findAny()
                        .map(Image::getPath)
                        .orElse(null));

        List<Double> prices = product.getVariants().stream()
                .map(Variant::getPrice).distinct().sorted().collect(Collectors.toList());

        clientListedProductResponse.setProductPriceRange(
                prices.size() == 0
                        ? Collections.emptyList()
                        : prices.size() == 1
                        ? List.of(prices.get(0))
                        : List.of(prices.get(0), prices.get(prices.size() - 1))
        );

        clientListedProductResponse.setProductVariants(product.getVariants().stream()
                .map(variant -> new ClientListedProductResponse.ClientListedVariantResponse()
                        .setVariantId(variant.getId())
                        .setVariantPrice(variant.getPrice())
                        .setVariantProperties(variant.getProperties()))
                .collect(Collectors.toList()));

        clientListedProductResponse.setProductSaleable(productInventories.stream()
                .filter(productInventory -> productInventory.getProductId().equals(product.getId()))
                .findAny()
                .map(productInventory -> productInventory.getCanBeSold() > 0)
                .orElse(false));

       

        return clientListedProductResponse;
    }

    public ClientProductResponse entityToResponse(Product product,
                                                  List<SimpleProductInventoryRequest> productInventories,
                                                  int averageRatingScore,
                                                  int countReviews,
                                                  List<ClientListedProductResponse> relatedProductResponses) {
        ClientProductResponse clientProductResponse = new ClientProductResponse();

        clientProductResponse.setProductId(product.getId());
        clientProductResponse.setProductName(product.getName());
        clientProductResponse.setProductSlug(product.getSlug());
        clientProductResponse.setProductShortDescription(product.getShortDescription());
        clientProductResponse.setProductDescription(product.getDescription());
        clientProductResponse.setProductImages(imageMapper.entityToResponse(product.getImages()));
        clientProductResponse.setProductCategory(clientCategoryMapper.entityToResponse(product.getCategory(), false));
        clientProductResponse.setProductBrand(product.getBrand() == null ? null : new ClientProductResponse.ClientBrandResponse()
                .setBrandId(product.getBrand().getId())
                .setBrandName(product.getBrand().getName()));
        clientProductResponse.setProductSpecifications(product.getSpecifications());
        clientProductResponse.setProductVariants(product.getVariants().stream()
                .map(variant -> new ClientProductResponse.ClientVariantResponse()
                        .setVariantId(variant.getId())
                        .setVariantPrice(variant.getPrice())
                        .setVariantProperties(variant.getProperties())
                        .setVariantInventory(InventoryUtils
                                .calculateInventoryIndices(docketVariantRepository.findByVariantId(variant.getId()))
                                .get("canBeSold")))
                .collect(Collectors.toList()));
        clientProductResponse.setProductSaleable(productInventories.stream()
                .filter(productInventory -> productInventory.getProductId().equals(product.getId()))
                .findAny()
                .map(productInventory -> productInventory.getCanBeSold() > 0)
                .orElse(false));
        clientProductResponse.setProductAverageRatingScore(averageRatingScore);
        clientProductResponse.setProductCountReviews(countReviews);
        clientProductResponse.setProductRelatedProducts(relatedProductResponses);
        

        return clientProductResponse;
    }

}

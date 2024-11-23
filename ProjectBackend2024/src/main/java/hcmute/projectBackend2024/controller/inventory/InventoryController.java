 package hcmute.projectBackend2024.controller.inventory;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.inventory.ProductInventoryResponse;
import hcmute.projectBackend2024.dto.inventory.VariantInventoryResponse;
import hcmute.projectBackend2024.dto.inventory.projection.ProductInventoryRequest;
import hcmute.projectBackend2024.dto.inventory.projection.VariantInventoryRequest;
import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.entity.variant.DocketVariant;
import hcmute.projectBackend2024.entity.variant.Variant;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.mapper.product.ProductInventoryMapper;
import hcmute.projectBackend2024.mapper.product.VariantInventoryMapper;
import hcmute.projectBackend2024.repository.inventory.DocketVariantRepository;
import hcmute.projectBackend2024.repository.product.ProductRepository;
import hcmute.projectBackend2024.repository.product.VariantRepository;
import hcmute.projectBackend2024.utils.InventoryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class InventoryController {

    private ProductRepository productRepository;
    private DocketVariantRepository docketVariantRepository;
    private ProductInventoryMapper productInventoryMapper;
    private VariantRepository variantRepository;
    private VariantInventoryMapper variantInventoryMapper;

    @GetMapping("/product-inventories")
    public ResponseEntity<ListResponse<ProductInventoryResponse>> getProductInventories(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {
        // Lấy danh sách sản phẩm từng được nhập xuất
        Page<Product> products = productRepository.findDocketedProducts(PageRequest.of(page - 1, size));

        List<ProductInventoryRequest> productInventories = new ArrayList<>();

        for (Product product : products) {
            ProductInventoryRequest productInventory = new ProductInventoryRequest();

            productInventory.setProduct(product);

            List<DocketVariant> transactions = docketVariantRepository.findByProductId(product.getId());
            productInventory.setTransactions(transactions);

            Map<String, Integer> inventoryIndices = InventoryUtils.calculateInventoryIndices(transactions);

            productInventory.setInventory(inventoryIndices.get("inventory"));
            productInventory.setWaitingForDelivery(inventoryIndices.get("waitingForDelivery"));
            productInventory.setCanBeSold(inventoryIndices.get("canBeSold"));
            productInventory.setAreComing(inventoryIndices.get("areComing"));

            productInventories.add(productInventory);
        }

        List<ProductInventoryResponse> productInventoryResponses = productInventoryMapper.toResponse(productInventories);

        return ResponseEntity.status(HttpStatus.OK).body(new ListResponse<>(productInventoryResponses, products));
    }
}

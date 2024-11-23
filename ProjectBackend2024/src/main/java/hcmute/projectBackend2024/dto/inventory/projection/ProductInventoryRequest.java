
package hcmute.projectBackend2024.dto.inventory.projection;

import lombok.Data;
import java.util.List;

import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.entity.variant.DocketVariant;

@Data
public class ProductInventoryRequest {
	private Product product;
	private List<DocketVariant> transactions;
	private Integer inventory;
	private Integer waitingForDelivery;
	private Integer canBeSold;
	private Integer areComing;
}

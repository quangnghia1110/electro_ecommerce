
package hcmute.projectBackend2024.dto.inventory.projection;

import lombok.Data;

@Data
public class SimpleProductInventoryRequest {
	private Long productId;
	private Integer inventory;
	private Integer waitingForDelivery;
	private Integer canBeSold;
	private Integer areComing;

	public SimpleProductInventoryRequest(Long productId, Long inventory, Long waitingForDelivery, Long canBeSold,
			Long areComing) {
		this.productId = productId;
		this.inventory = Math.toIntExact(inventory);
		this.waitingForDelivery = Math.toIntExact(waitingForDelivery);
		this.canBeSold = Math.toIntExact(canBeSold);
		this.areComing = Math.toIntExact(areComing);
	}
}

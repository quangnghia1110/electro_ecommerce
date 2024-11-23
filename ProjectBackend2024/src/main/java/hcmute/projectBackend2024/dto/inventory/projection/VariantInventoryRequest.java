package hcmute.projectBackend2024.dto.inventory.projection;

import lombok.Data;
import java.util.List;

import hcmute.projectBackend2024.entity.variant.DocketVariant;
import hcmute.projectBackend2024.entity.variant.Variant;

@Data
public class VariantInventoryRequest {
	private Variant variant;
	private List<DocketVariant> transactions;
	private Integer inventory;
	private Integer waitingForDelivery;
	private Integer canBeSold;
	private Integer areComing;
}

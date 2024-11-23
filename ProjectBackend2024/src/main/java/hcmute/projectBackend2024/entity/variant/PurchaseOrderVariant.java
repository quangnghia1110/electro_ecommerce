package hcmute.projectBackend2024.entity.variant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hcmute.projectBackend2024.entity.inventory.PurchaseOrder;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "purchase_order_variant")
public class PurchaseOrderVariant {

    @EmbeddedId
    private PurchaseOrderVariantKey purchaseOrderVariantKey = new PurchaseOrderVariantKey();

    @ManyToOne
    @MapsId("purchaseOrderId")
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class PurchaseOrderVariantKey implements Serializable {

        @Column(name = "purchase_order_id", nullable = false)
        private Long purchaseOrderId;

        @Column(name = "variant_id", nullable = false)
        private Long variantId;
    }
}

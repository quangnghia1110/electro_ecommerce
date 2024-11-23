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

import hcmute.projectBackend2024.entity.order.Order;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "order_variant")
public class OrderVariant {
    @EmbeddedId
    private OrderVariantKey orderVariantKey = new OrderVariantKey();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(15,5)")
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(15,5)")
    private BigDecimal amount;

    // OrderVariantKey class
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class OrderVariantKey implements Serializable {
        @Column(name = "order_id", nullable = false)
        Long orderId;

        @Column(name = "variant_id", nullable = false)
        Long variantId;
    }
}

package hcmute.projectBackend2024.entity.variant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hcmute.projectBackend2024.entity.cart.Cart;

import java.io.Serializable;
import java.time.Instant;

// CartVariant class with embedded key class
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cart_variant")
public class CartVariant {

    @EmbeddedId
    private CartVariantKey cartVariantKey = new CartVariantKey();

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // CartVariantKey class
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class CartVariantKey implements Serializable {
        @Column(name = "cart_id", nullable = false)
        Long cartId;

        @Column(name = "variant_id", nullable = false)
        Long variantId;
    }
}

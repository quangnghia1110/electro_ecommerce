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
import java.io.Serializable;

import hcmute.projectBackend2024.entity.inventory.Docket;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "docket_variant")
public class DocketVariant {
    @EmbeddedId
    private DocketVariantKey docketVariantKey = new DocketVariantKey();

    @ManyToOne
    @MapsId("docketId")
    @JoinColumn(name = "docket_id", nullable = false)
    private Docket docket;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // DocketVariantKey class
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class DocketVariantKey implements Serializable {
        @Column(name = "docket_id", nullable = false)
        Long docketId;

        @Column(name = "variant_id", nullable = false)
        Long variantId;
    }
}

package hcmute.projectBackend2024.repository.inventory;

import java.util.List;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.entity.variant.DocketVariant;
import hcmute.projectBackend2024.entity.variant.DocketVariant.DocketVariantKey;
import hcmute.projectBackend2024.entity.variant.Variant;

public interface DocketVariantRepository extends JpaRepository<DocketVariant, DocketVariantKey>,
        JpaSpecificationExecutor<DocketVariant> {

    default List<DocketVariant> findByProductId(Long productId) {
        Specification<DocketVariant> spec = (root, query, cb) -> {
            Join<DocketVariant, Variant> variant = root.join("variant");
            Join<Variant, Product> product = variant.join("product");

            query.where(cb.equal(product.get("id"), productId));
            query.orderBy(cb.desc(root.get("docket").get("id")));

            return query.getRestriction();
        };

        return findAll(spec);
    }

    default List<DocketVariant> findByVariantId(Long variantId) {
        Specification<DocketVariant> spec = (root, query, cb) -> query
                .where(cb.equal(root.get("variant").get("id"), variantId))
                .orderBy(cb.desc(root.get("docket").get("id")))
                .getRestriction();

        return findAll(spec);
    }

}

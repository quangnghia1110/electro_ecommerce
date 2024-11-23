package hcmute.projectBackend2024.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.variant.DocketVariant;
import hcmute.projectBackend2024.entity.variant.Variant;

import javax.persistence.criteria.Join;

public interface VariantRepository extends JpaRepository<Variant, Long>, JpaSpecificationExecutor<Variant> {

    default Page<Variant> findDocketedVariants(Pageable pageable) {
        Specification<Variant> spec = (root, query, cb) -> {
            Join<Variant, DocketVariant> docketVariant = root.join("docketVariants");

            query.distinct(true);
            query.orderBy(cb.desc(docketVariant.get("docket").get("id")));

            return query.getRestriction();
        };

        return findAll(spec, pageable);
    }

}

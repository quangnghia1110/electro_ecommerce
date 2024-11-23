package hcmute.projectBackend2024.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hcmute.projectBackend2024.entity.product.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

    @Query("SELECT COUNT(s.id) FROM Supplier s")
    int countBySupplierId();

}

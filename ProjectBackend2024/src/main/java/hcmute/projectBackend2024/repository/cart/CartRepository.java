package hcmute.projectBackend2024.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hcmute.projectBackend2024.entity.cart.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

    @Query("SELECT c FROM Cart c JOIN c.user u WHERE u.username = :username AND c.status = 1")
    Optional<Cart> findByUsername(@Param("username") String username);

}

package hcmute.projectBackend2024.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.order.OrderResource;

public interface OrderResourceRepository extends JpaRepository<OrderResource, Long>, JpaSpecificationExecutor<OrderResource> {}

package hcmute.projectBackend2024.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.inventory.Docket;

public interface DocketRepository extends JpaRepository<Docket, Long>, JpaSpecificationExecutor<Docket> {}

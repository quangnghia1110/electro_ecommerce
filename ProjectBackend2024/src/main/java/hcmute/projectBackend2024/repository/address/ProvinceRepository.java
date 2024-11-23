package hcmute.projectBackend2024.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.address.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long>, JpaSpecificationExecutor<Province> {
}

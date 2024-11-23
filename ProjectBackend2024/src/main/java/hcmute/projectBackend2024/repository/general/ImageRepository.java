package hcmute.projectBackend2024.repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.general.Image;

public interface ImageRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {}

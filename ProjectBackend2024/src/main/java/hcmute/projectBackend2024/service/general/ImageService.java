package hcmute.projectBackend2024.service.general;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import hcmute.projectBackend2024.dto.general.UploadedImageResponse;

public interface ImageService {

    UploadedImageResponse store(MultipartFile image);

    Resource load(String imageName);

    void delete(String imageName);

}

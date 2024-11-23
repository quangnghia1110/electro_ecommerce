package hcmute.projectBackend2024.controller.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.dto.CollectionWrapper;
import hcmute.projectBackend2024.dto.client.ClientCategoryResponse;
import hcmute.projectBackend2024.entity.product.Category;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.mapper.client.ClientCategoryMapper;
import hcmute.projectBackend2024.repository.product.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/client-api/categories")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ClientCategoryController {

    private CategoryRepository categoryRepository;
    private ClientCategoryMapper clientCategoryMapper;

    @GetMapping
    public ResponseEntity<CollectionWrapper<ClientCategoryResponse>> getAllCategories() {
        List<Category> firstCategories = categoryRepository.findByParentCategoryIsNull();
        List<ClientCategoryResponse> clientCategoryResponses = clientCategoryMapper.entityToResponse(firstCategories, 3);
        return ResponseEntity.status(HttpStatus.OK).body(CollectionWrapper.of(clientCategoryResponses));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ClientCategoryResponse> getCategory(@PathVariable("slug") String slug) {
        ClientCategoryResponse clientCategoryResponse = categoryRepository.findBySlug(slug)
                .map(category -> clientCategoryMapper.entityToResponse(category, false))
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CATEGORY, FieldName.SLUG, slug));
        return ResponseEntity.status(HttpStatus.OK).body(clientCategoryResponse);
    }

}

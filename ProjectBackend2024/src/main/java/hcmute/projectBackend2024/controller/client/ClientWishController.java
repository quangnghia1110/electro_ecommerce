package hcmute.projectBackend2024.controller.client;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.client.ClientWishRequest;
import hcmute.projectBackend2024.dto.client.ClientWishResponse;
import hcmute.projectBackend2024.entity.client.Wish;
import hcmute.projectBackend2024.mapper.client.ClientWishMapper;
import hcmute.projectBackend2024.repository.client.WishRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client-api/wishes")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ClientWishController {

    private WishRepository wishRepository;
    private ClientWishMapper clientWishMapper;

    @GetMapping
    public ResponseEntity<ListResponse<ClientWishResponse>> getAllWishes(
            Authentication authentication,
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter
    ) {
        String username = authentication.getName();
        Page<Wish> wishes = wishRepository.findAllByUsername(username, sort, filter, PageRequest.of(page - 1, size));
        List<ClientWishResponse> clientWishResponses = wishes.map(clientWishMapper::entityToResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(ListResponse.of(clientWishResponses, wishes));
    }

    @PostMapping
    public ResponseEntity<ClientWishResponse> createWish(@RequestBody ClientWishRequest request) throws Exception {
        Optional<Wish> wishOpt = wishRepository.findByUser_IdAndProduct_Id(request.getUserId(), request.getProductId());

        if (wishOpt.isPresent()) {
            throw new Exception("Duplicated wish");
        } else {
            Wish entity = clientWishMapper.requestToEntity(request);
            entity = wishRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(clientWishMapper.entityToResponse(entity));
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteWishes(@RequestBody List<Long> ids) {
        wishRepository.deleteAllById(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

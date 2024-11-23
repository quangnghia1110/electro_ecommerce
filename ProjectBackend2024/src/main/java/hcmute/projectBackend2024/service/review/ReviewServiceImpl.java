package hcmute.projectBackend2024.service.review;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.constant.SearchFields;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.review.ReviewRequest;
import hcmute.projectBackend2024.dto.review.ReviewResponse;
import hcmute.projectBackend2024.entity.review.Review;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.mapper.review.ReviewMapper;
import hcmute.projectBackend2024.repository.review.ReviewRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    @Override
    public ListResponse<ReviewResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.REVIEW, reviewRepository, reviewMapper);
    }

    @Override
    public ReviewResponse findById(Long id) {
        return defaultFindById(id, reviewRepository, reviewMapper, ResourceName.REVIEW);
    }

    @Override
    public ReviewResponse save(ReviewRequest request) {
        return defaultSave(request, reviewRepository, reviewMapper);
    }

    @Override
    public ReviewResponse save(Long id, ReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .map(existingEntity -> reviewMapper.partialUpdate(existingEntity, request))
                .map(reviewRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DOCKET, FieldName.ID, id));


        return reviewMapper.entityToResponse(review);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        reviewRepository.deleteAllById(ids);
    }

}

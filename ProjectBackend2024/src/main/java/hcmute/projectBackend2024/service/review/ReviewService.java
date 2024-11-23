package hcmute.projectBackend2024.service.review;

import hcmute.projectBackend2024.dto.review.ReviewRequest;
import hcmute.projectBackend2024.dto.review.ReviewResponse;
import hcmute.projectBackend2024.generic.CrudService;

public interface ReviewService extends CrudService<Long, ReviewRequest, ReviewResponse> {}

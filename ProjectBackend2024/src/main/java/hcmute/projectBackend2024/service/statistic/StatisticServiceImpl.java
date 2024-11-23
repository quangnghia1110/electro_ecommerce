package hcmute.projectBackend2024.service.statistic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.dto.statistic.StatisticResource;
import hcmute.projectBackend2024.dto.statistic.StatisticResponse;
import hcmute.projectBackend2024.repository.authentication.UserProjectionRepository;
import hcmute.projectBackend2024.repository.order.OrderProjectionRepository;
import hcmute.projectBackend2024.repository.order.OrderRepository;
import hcmute.projectBackend2024.repository.product.BrandRepository;
import hcmute.projectBackend2024.repository.product.ProductRepository;
import hcmute.projectBackend2024.repository.product.SupplierRepository;
import hcmute.projectBackend2024.repository.review.ReviewProjectionRepository;
import hcmute.projectBackend2024.repository.review.ReviewRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private SupplierRepository supplierRepository;
    private BrandRepository brandRepository;
    private ReviewRepository reviewRepository;
    private UserProjectionRepository userProjectionRepository;
    private OrderProjectionRepository orderProjectionRepository;
    private ReviewProjectionRepository reviewProjectionRepository;

    @Override
    public StatisticResponse getStatistic() {
        StatisticResponse statisticResponse = new StatisticResponse();

        // TODO: Nên dùng tên hàm `count` hợp lý hơn, như `countAll()`
        int totalProduct = productRepository.countByProductId();
        int totalOrder = orderRepository.countByOrderId();
        int totalReview = reviewRepository.countByReviewId();
        int totalSupplier = supplierRepository.countBySupplierId();
        int totalBrand = brandRepository.countByBrandId();

        List<StatisticResource> statisticRegistration = userProjectionRepository.getUserCountByCreateDate();
        List<StatisticResource> statisticOrder = orderProjectionRepository.getOrderCountByCreateDate();
        List<StatisticResource> statisticReview = reviewProjectionRepository.getReviewCountByCreateDate();

        statisticResponse.setTotalProduct(totalProduct);
        statisticResponse.setTotalOrder(totalOrder);
        statisticResponse.setTotalReview(totalReview);
        statisticResponse.setTotalSupplier(totalSupplier);
        statisticResponse.setTotalBrand(totalBrand);
        statisticResponse.setStatisticRegistration(statisticRegistration);
        statisticResponse.setStatisticOrder(statisticOrder);
        statisticResponse.setStatisticReview(statisticReview);

        return statisticResponse;
    }

}

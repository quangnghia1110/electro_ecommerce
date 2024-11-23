package hcmute.projectBackend2024.repository.client;

import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hcmute.projectBackend2024.entity.client.Wish;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long>, JpaSpecificationExecutor<Wish> {

    default Page<Wish> findAllByUsername(String username, String sort, String filter, Pageable pageable) {
        Specification<Wish> sortable = RSQLJPASupport.toSort(sort);
        Specification<Wish> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Wish> usernameSpec = RSQLJPASupport.toSpecification("user.username==" + username);
        return findAll(sortable.and(filterable).and(usernameSpec), pageable);
    }

    //Kiểm tra sản phẩm đó có trong wish chưa
    Optional<Wish> findByUser_IdAndProduct_Id(Long userId, Long productId);

}

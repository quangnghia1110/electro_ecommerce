package hcmute.projectBackend2024.utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import hcmute.projectBackend2024.entity.BaseEntity;
import hcmute.projectBackend2024.entity.address.District;
import hcmute.projectBackend2024.entity.address.Province;
import hcmute.projectBackend2024.entity.address.Ward;
import hcmute.projectBackend2024.entity.authentication.User;
import hcmute.projectBackend2024.entity.chat.Room;
import hcmute.projectBackend2024.entity.inventory.Destination;
import hcmute.projectBackend2024.entity.inventory.Docket;
import hcmute.projectBackend2024.entity.inventory.DocketReason;
import hcmute.projectBackend2024.entity.inventory.PurchaseOrder;
import hcmute.projectBackend2024.entity.inventory.Warehouse;
import hcmute.projectBackend2024.entity.order.Order;
import hcmute.projectBackend2024.entity.order.OrderCancellationReason;
import hcmute.projectBackend2024.entity.order.OrderResource;
import hcmute.projectBackend2024.entity.product.Brand;
import hcmute.projectBackend2024.entity.product.Category;
import hcmute.projectBackend2024.entity.product.Guarantee;
import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.entity.product.Supplier;
import hcmute.projectBackend2024.entity.product.Unit;
import hcmute.projectBackend2024.entity.variant.DocketVariant.DocketVariantKey;
import hcmute.projectBackend2024.entity.variant.OrderVariant.OrderVariantKey;
import hcmute.projectBackend2024.entity.variant.PurchaseOrderVariant.PurchaseOrderVariantKey;
import hcmute.projectBackend2024.entity.variant.Variant;
import hcmute.projectBackend2024.repository.address.DistrictRepository;
import hcmute.projectBackend2024.repository.address.ProvinceRepository;
import hcmute.projectBackend2024.repository.address.WardRepository;
import hcmute.projectBackend2024.repository.authentication.RoleRepository;
import hcmute.projectBackend2024.repository.authentication.UserRepository;
import hcmute.projectBackend2024.repository.product.ProductRepository;
import hcmute.projectBackend2024.repository.product.TagRepository;
import hcmute.projectBackend2024.repository.product.VariantRepository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MapperUtils {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private UserRepository userRepository;

    public Province mapToProvince(@Nullable Long id) {
        return id == null ? null : provinceRepository.getById(id);
    }

    public District mapToDistrict(@Nullable Long id) {
        return id == null ? null : districtRepository.getById(id);
    }

    public Ward mapToWard(@Nullable Long id) {
        return id == null ? null : wardRepository.getById(id);
    }

 
    public abstract Category mapToCategory(Long id);

    public abstract Brand mapToBrand(Long id);

    public abstract Supplier mapToSupplier(Long id);

    public abstract Unit mapToUnit(Long id);

    public abstract Guarantee mapToGuarantee(Long id);

    public abstract Warehouse mapToWarehouse(Long id);

    public abstract DocketReason mapToDocketReason(Long id);

    public abstract Destination mapToDestination(Long id);

    public abstract PurchaseOrder mapToPurchaseOrder(Long id);

    public abstract OrderResource mapToOrderResource(Long id);

    public abstract OrderCancellationReason mapToOrderCancellationReason(Long id);


    public abstract Order mapToOrder(Long id);

    public abstract Room mapToRoom(Long id);

    public Variant mapToVariant(Long id) {
        return variantRepository.getById(id);
    }

    public Product mapToProduct(Long id) {
        return productRepository.getById(id);
    }

    public User mapToUser(Long id) {
        return userRepository.getById(id);
    }

    @Named("hashPassword")
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @AfterMapping
    @Named("attachUser")
    public User attachUser(@MappingTarget User user) {
        return user.setRoles(attachSet(user.getRoles(), roleRepository));
    }

    @AfterMapping
    @Named("attachProduct")
    public Product attachProduct(@MappingTarget Product product) {
        product.getImages().forEach(image -> image.setProduct(product));
        product.setTags(attachSet(product.getTags(), tagRepository));
        product.getVariants().forEach(variant -> variant.setProduct(product));
        return product;
    }

    

    @AfterMapping
    @Named("attachOrder")
    public Order attachOrder(@MappingTarget Order order) {
        order.getOrderVariants().forEach(orderVariant -> {
            orderVariant.setOrderVariantKey(new OrderVariantKey(order.getId(), orderVariant.getVariant().getId()));
            orderVariant.setOrder(order);
        });
        return order;
    }

    @AfterMapping
    @Named("attachDocket")
    public Docket attachDocket(@MappingTarget Docket docket) {
        docket.getDocketVariants().forEach(docketVariant -> {
            docketVariant.setDocketVariantKey(new DocketVariantKey(docket.getId(), docketVariant.getVariant().getId()));
            docketVariant.setDocket(docket);
        });
        return docket;
    }

    @AfterMapping
    @Named("attachPurchaseOrder")
    public PurchaseOrder attachPurchaseOrder(@MappingTarget PurchaseOrder purchaseOrder) {
        purchaseOrder.getPurchaseOrderVariants().forEach(purchaseOrderVariant -> {
            purchaseOrderVariant.setPurchaseOrderVariantKey(
                    new PurchaseOrderVariantKey(purchaseOrder.getId(), purchaseOrderVariant.getVariant().getId()));
            purchaseOrderVariant.setPurchaseOrder(purchaseOrder);
        });
        return purchaseOrder;
    }

    private <E extends BaseEntity> Set<E> attachSet(Set<E> entities, JpaRepository<E, Long> repository) {
        Set<E> detachedSet = Optional.ofNullable(entities).orElseGet(HashSet::new);
        Set<E> attachedSet = new HashSet<>();

        for (E entity : detachedSet) {
            if (entity.getId() != null) {
                repository.findById(entity.getId()).ifPresent(attachedSet::add);
            } else {
                attachedSet.add(entity);
            }
        }

        return attachedSet;
    }

}

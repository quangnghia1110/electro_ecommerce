//Nó sử dụng RequestMappingHandlerMapping để ánh xạ các đường dẫn API đến các phương thức trong GenericController.
package hcmute.projectBackend2024.generic;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import com.fasterxml.jackson.databind.JsonNode;

import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.constant.SearchFields;
import hcmute.projectBackend2024.dto.address.AddressRequest;
import hcmute.projectBackend2024.dto.address.AddressResponse;
import hcmute.projectBackend2024.dto.address.DistrictRequest;
import hcmute.projectBackend2024.dto.address.DistrictResponse;
import hcmute.projectBackend2024.dto.address.ProvinceRequest;
import hcmute.projectBackend2024.dto.address.ProvinceResponse;
import hcmute.projectBackend2024.dto.address.WardRequest;
import hcmute.projectBackend2024.dto.address.WardResponse;
import hcmute.projectBackend2024.dto.authentication.RoleRequest;
import hcmute.projectBackend2024.dto.authentication.RoleResponse;
import hcmute.projectBackend2024.dto.authentication.UserRequest;
import hcmute.projectBackend2024.dto.authentication.UserResponse;
import hcmute.projectBackend2024.dto.cashbook.PaymentMethodRequest;
import hcmute.projectBackend2024.dto.cashbook.PaymentMethodResponse;
import hcmute.projectBackend2024.dto.chat.RoomRequest;
import hcmute.projectBackend2024.dto.chat.RoomResponse;
import hcmute.projectBackend2024.dto.general.ImageRequest;
import hcmute.projectBackend2024.dto.general.ImageResponse;
import hcmute.projectBackend2024.dto.inventory.DestinationRequest;
import hcmute.projectBackend2024.dto.inventory.DestinationResponse;
import hcmute.projectBackend2024.dto.inventory.DocketReasonRequest;
import hcmute.projectBackend2024.dto.inventory.DocketReasonResponse;
import hcmute.projectBackend2024.dto.inventory.DocketRequest;
import hcmute.projectBackend2024.dto.inventory.DocketResponse;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderRequest;
import hcmute.projectBackend2024.dto.inventory.PurchaseOrderResponse;
import hcmute.projectBackend2024.dto.inventory.WarehouseRequest;
import hcmute.projectBackend2024.dto.inventory.WarehouseResponse;
import hcmute.projectBackend2024.dto.order.OrderCancellationReasonRequest;
import hcmute.projectBackend2024.dto.order.OrderCancellationReasonResponse;
import hcmute.projectBackend2024.dto.order.OrderRequest;
import hcmute.projectBackend2024.dto.order.OrderResourceRequest;
import hcmute.projectBackend2024.dto.order.OrderResourceResponse;
import hcmute.projectBackend2024.dto.order.OrderResponse;
import hcmute.projectBackend2024.dto.product.BrandRequest;
import hcmute.projectBackend2024.dto.product.BrandResponse;
import hcmute.projectBackend2024.dto.product.CategoryRequest;
import hcmute.projectBackend2024.dto.product.CategoryResponse;
import hcmute.projectBackend2024.dto.product.GuaranteeRequest;
import hcmute.projectBackend2024.dto.product.GuaranteeResponse;
import hcmute.projectBackend2024.dto.product.ProductRequest;
import hcmute.projectBackend2024.dto.product.ProductResponse;
import hcmute.projectBackend2024.dto.product.PropertyRequest;
import hcmute.projectBackend2024.dto.product.PropertyResponse;
import hcmute.projectBackend2024.dto.product.SpecificationRequest;
import hcmute.projectBackend2024.dto.product.SpecificationResponse;
import hcmute.projectBackend2024.dto.product.SupplierRequest;
import hcmute.projectBackend2024.dto.product.SupplierResponse;
import hcmute.projectBackend2024.dto.product.TagRequest;
import hcmute.projectBackend2024.dto.product.TagResponse;
import hcmute.projectBackend2024.dto.product.UnitRequest;
import hcmute.projectBackend2024.dto.product.UnitResponse;
import hcmute.projectBackend2024.dto.product.VariantRequest;
import hcmute.projectBackend2024.dto.product.VariantResponse;
import hcmute.projectBackend2024.dto.review.ReviewRequest;
import hcmute.projectBackend2024.dto.review.ReviewResponse;
import hcmute.projectBackend2024.entity.address.Address;
import hcmute.projectBackend2024.entity.address.District;
import hcmute.projectBackend2024.entity.address.Ward;
import hcmute.projectBackend2024.entity.authentication.Role;
import hcmute.projectBackend2024.entity.authentication.User;
import hcmute.projectBackend2024.entity.cashbook.PaymentMethod;
import hcmute.projectBackend2024.entity.chat.Room;
import hcmute.projectBackend2024.entity.general.Image;
import hcmute.projectBackend2024.entity.inventory.Destination;
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
import hcmute.projectBackend2024.entity.product.Property;
import hcmute.projectBackend2024.entity.product.Specification;
import hcmute.projectBackend2024.entity.product.Supplier;
import hcmute.projectBackend2024.entity.product.Tag;
import hcmute.projectBackend2024.entity.product.Unit;
import hcmute.projectBackend2024.entity.variant.Variant;
import hcmute.projectBackend2024.mapper.address.AddressMapper;
import hcmute.projectBackend2024.mapper.address.DistrictMapper;
import hcmute.projectBackend2024.mapper.address.WardMapper;
import hcmute.projectBackend2024.mapper.authentication.RoleMapper;
import hcmute.projectBackend2024.mapper.authentication.UserMapper;
import hcmute.projectBackend2024.mapper.cashbook.PaymentMethodMapper;
import hcmute.projectBackend2024.mapper.chat.RoomMapper;
import hcmute.projectBackend2024.mapper.general.ImageMapper;
import hcmute.projectBackend2024.mapper.inventory.DestinationMapper;
import hcmute.projectBackend2024.mapper.inventory.DocketReasonMapper;
import hcmute.projectBackend2024.mapper.inventory.PurchaseOrderMapper;
import hcmute.projectBackend2024.mapper.inventory.WarehouseMapper;
import hcmute.projectBackend2024.mapper.order.OrderCancellationReasonMapper;
import hcmute.projectBackend2024.mapper.order.OrderMapper;
import hcmute.projectBackend2024.mapper.order.OrderResourceMapper;
import hcmute.projectBackend2024.mapper.product.BrandMapper;
import hcmute.projectBackend2024.mapper.product.CategoryMapper;
import hcmute.projectBackend2024.mapper.product.GuaranteeMapper;
import hcmute.projectBackend2024.mapper.product.ProductMapper;
import hcmute.projectBackend2024.mapper.product.PropertyMapper;
import hcmute.projectBackend2024.mapper.product.SpecificationMapper;
import hcmute.projectBackend2024.mapper.product.SupplierMapper;
import hcmute.projectBackend2024.mapper.product.TagMapper;
import hcmute.projectBackend2024.mapper.product.UnitMapper;
import hcmute.projectBackend2024.mapper.product.VariantMapper;
import hcmute.projectBackend2024.repository.address.AddressRepository;
import hcmute.projectBackend2024.repository.address.DistrictRepository;
import hcmute.projectBackend2024.repository.address.WardRepository;
import hcmute.projectBackend2024.repository.authentication.RoleRepository;
import hcmute.projectBackend2024.repository.authentication.UserRepository;
import hcmute.projectBackend2024.repository.cashbook.PaymentMethodRepository;
import hcmute.projectBackend2024.repository.chat.RoomRepository;
import hcmute.projectBackend2024.repository.general.ImageRepository;
import hcmute.projectBackend2024.repository.inventory.DestinationRepository;
import hcmute.projectBackend2024.repository.inventory.DocketReasonRepository;
import hcmute.projectBackend2024.repository.inventory.PurchaseOrderRepository;
import hcmute.projectBackend2024.repository.inventory.WarehouseRepository;
import hcmute.projectBackend2024.repository.order.OrderCancellationReasonRepository;
import hcmute.projectBackend2024.repository.order.OrderRepository;
import hcmute.projectBackend2024.repository.order.OrderResourceRepository;
import hcmute.projectBackend2024.repository.product.BrandRepository;
import hcmute.projectBackend2024.repository.product.CategoryRepository;
import hcmute.projectBackend2024.repository.product.GuaranteeRepository;
import hcmute.projectBackend2024.repository.product.ProductRepository;
import hcmute.projectBackend2024.repository.product.PropertyRepository;
import hcmute.projectBackend2024.repository.product.SpecificationRepository;
import hcmute.projectBackend2024.repository.product.SupplierRepository;
import hcmute.projectBackend2024.repository.product.TagRepository;
import hcmute.projectBackend2024.repository.product.UnitRepository;
import hcmute.projectBackend2024.repository.product.VariantRepository;
import hcmute.projectBackend2024.service.address.ProvinceService;
import hcmute.projectBackend2024.service.inventory.DocketService;
import hcmute.projectBackend2024.service.review.ReviewService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GenericMappingRegister {

    private ApplicationContext context;
    private RequestMappingHandlerMapping handlerMapping;

    // Controllers
    private GenericController<ProvinceRequest, ProvinceResponse> provinceController;
    private GenericController<DistrictRequest, DistrictResponse> districtController;
    private GenericController<WardRequest, WardResponse> wardController;
    private GenericController<AddressRequest, AddressResponse> addressController;
    private GenericController<UserRequest, UserResponse> userController;
    private GenericController<RoleRequest, RoleResponse> roleController;
    private GenericController<PropertyRequest, PropertyResponse> propertyController;
    private GenericController<CategoryRequest, CategoryResponse> categoryController;
    private GenericController<TagRequest, TagResponse> tagController;
    private GenericController<GuaranteeRequest, GuaranteeResponse> guaranteeController;
    private GenericController<UnitRequest, UnitResponse> unitController;
    private GenericController<SupplierRequest, SupplierResponse> supplierController;
    private GenericController<BrandRequest, BrandResponse> brandController;
    private GenericController<SpecificationRequest, SpecificationResponse> specificationController;
    private GenericController<ProductRequest, ProductResponse> productController;
    private GenericController<VariantRequest, VariantResponse> variantController;
    private GenericController<ImageRequest, ImageResponse> imageController;
    private GenericController<WarehouseRequest, WarehouseResponse> warehouseController;
    private GenericController<DestinationRequest, DestinationResponse> destinationController;
    private GenericController<DocketReasonRequest, DocketReasonResponse> docketReasonController;
    private GenericController<DocketRequest, DocketResponse> docketController;
    private GenericController<PurchaseOrderRequest, PurchaseOrderResponse> purchaseOrderController;
    private GenericController<OrderResourceRequest, OrderResourceResponse> orderResourceController;
    private GenericController<OrderCancellationReasonRequest, OrderCancellationReasonResponse> orderCancellationReasonController;
    private GenericController<OrderRequest, OrderResponse> orderController;
    private GenericController<ReviewRequest, ReviewResponse> reviewController;
    private GenericController<PaymentMethodRequest, PaymentMethodResponse> paymentMethodController;
    private GenericController<RoomRequest, RoomResponse> roomController;

    // Services
    private GenericService<District, DistrictRequest, DistrictResponse> districtService;
    private GenericService<Ward, WardRequest, WardResponse> wardService;
    private GenericService<Address, AddressRequest, AddressResponse> addressService;
    private GenericService<User, UserRequest, UserResponse> userService;
    private GenericService<Role, RoleRequest, RoleResponse> roleService;
    private GenericService<Property, PropertyRequest, PropertyResponse> propertyService;
    private GenericService<Category, CategoryRequest, CategoryResponse> categoryService;
    private GenericService<Tag, TagRequest, TagResponse> tagService;
    private GenericService<Guarantee, GuaranteeRequest, GuaranteeResponse> guaranteeService;
    private GenericService<Unit, UnitRequest, UnitResponse> unitService;
    private GenericService<Supplier, SupplierRequest, SupplierResponse> supplierService;
    private GenericService<Brand, BrandRequest, BrandResponse> brandService;
    private GenericService<Specification, SpecificationRequest, SpecificationResponse> specificationService;
    private GenericService<Product, ProductRequest, ProductResponse> productService;
    private GenericService<Variant, VariantRequest, VariantResponse> variantService;
    private GenericService<Image, ImageRequest, ImageResponse> imageService;
    private GenericService<Warehouse, WarehouseRequest, WarehouseResponse> warehouseService;
    private GenericService<Destination, DestinationRequest, DestinationResponse> destinationService;
    private GenericService<DocketReason, DocketReasonRequest, DocketReasonResponse> docketReasonService;
    private GenericService<PurchaseOrder, PurchaseOrderRequest, PurchaseOrderResponse> purchaseOrderService;
    private GenericService<OrderResource, OrderResourceRequest, OrderResourceResponse> orderResourceService;
    private GenericService<OrderCancellationReason, OrderCancellationReasonRequest, OrderCancellationReasonResponse> orderCancellationReasonService;
    private GenericService<Order, OrderRequest, OrderResponse> orderService;
    private GenericService<PaymentMethod, PaymentMethodRequest, PaymentMethodResponse> paymentMethodService;
    private GenericService<Room, RoomRequest, RoomResponse> roomService;

    @PostConstruct
    public void registerControllers() throws NoSuchMethodException {

        // Các đăng ký có sử dụng init
        register("districts", districtController, districtService.init(
                context.getBean(DistrictRepository.class),
                context.getBean(DistrictMapper.class),
                SearchFields.DISTRICT,
                ResourceName.DISTRICT
        ), DistrictRequest.class);

        register("wards", wardController, wardService.init(
                context.getBean(WardRepository.class),
                context.getBean(WardMapper.class),
                SearchFields.WARD,
                ResourceName.WARD
        ), WardRequest.class);

        register("addresses", addressController, addressService.init(
                context.getBean(AddressRepository.class),
                context.getBean(AddressMapper.class),
                SearchFields.ADDRESS,
                ResourceName.ADDRESS
        ), AddressRequest.class);

        register("users", userController, userService.init(
                context.getBean(UserRepository.class),
                context.getBean(UserMapper.class),
                SearchFields.USER,
                ResourceName.USER
        ), UserRequest.class);

        register("roles", roleController, roleService.init(
                context.getBean(RoleRepository.class),
                context.getBean(RoleMapper.class),
                SearchFields.ROLE,
                ResourceName.ROLE
        ), RoleRequest.class);

        
        register("properties", propertyController, propertyService.init(
                context.getBean(PropertyRepository.class),
                context.getBean(PropertyMapper.class),
                SearchFields.PROPERTY,
                ResourceName.PROPERTY
        ), PropertyRequest.class);

        register("categories", categoryController, categoryService.init(
                context.getBean(CategoryRepository.class),
                context.getBean(CategoryMapper.class),
                SearchFields.CATEGORY,
                ResourceName.CATEGORY
        ), CategoryRequest.class);

        register("tags", tagController, tagService.init(
                context.getBean(TagRepository.class),
                context.getBean(TagMapper.class),
                SearchFields.TAG,
                ResourceName.TAG
        ), TagRequest.class);

        register("guarantees", guaranteeController, guaranteeService.init(
                context.getBean(GuaranteeRepository.class),
                context.getBean(GuaranteeMapper.class),
                SearchFields.GUARANTEE,
                ResourceName.GUARANTEE
        ), GuaranteeRequest.class);

        register("units", unitController, unitService.init(
                context.getBean(UnitRepository.class),
                context.getBean(UnitMapper.class),
                SearchFields.UNIT,
                ResourceName.UNIT
        ), UnitRequest.class);

        register("suppliers", supplierController, supplierService.init(
                context.getBean(SupplierRepository.class),
                context.getBean(SupplierMapper.class),
                SearchFields.SUPPLIER,
                ResourceName.SUPPLIER
        ), SupplierRequest.class);

        register("brands", brandController, brandService.init(
                context.getBean(BrandRepository.class),
                context.getBean(BrandMapper.class),
                SearchFields.BRAND,
                ResourceName.BRAND
        ), BrandRequest.class);

        register("specifications", specificationController, specificationService.init(
                context.getBean(SpecificationRepository.class),
                context.getBean(SpecificationMapper.class),
                SearchFields.SPECIFICATION,
                ResourceName.SPECIFICATION
        ), SpecificationRequest.class);

        register("products", productController, productService.init(
                context.getBean(ProductRepository.class),
                context.getBean(ProductMapper.class),
                SearchFields.PRODUCT,
                ResourceName.PRODUCT
        ), ProductRequest.class);

        register("variants", variantController, variantService.init(
                context.getBean(VariantRepository.class),
                context.getBean(VariantMapper.class),
                SearchFields.VARIANT,
                ResourceName.VARIANT
        ), VariantRequest.class);

        register("images", imageController, imageService.init(
                context.getBean(ImageRepository.class),
                context.getBean(ImageMapper.class),
                SearchFields.IMAGE,
                ResourceName.IMAGE
        ), ImageRequest.class);

        

        register("warehouses", warehouseController, warehouseService.init(
                context.getBean(WarehouseRepository.class),
                context.getBean(WarehouseMapper.class),
                SearchFields.WAREHOUSE,
                ResourceName.WAREHOUSE
        ), WarehouseRequest.class);

       

        register("destinations", destinationController, destinationService.init(
                context.getBean(DestinationRepository.class),
                context.getBean(DestinationMapper.class),
                SearchFields.DESTINATION,
                ResourceName.DESTINATION
        ), DestinationRequest.class);

        register("docket-reasons", docketReasonController, docketReasonService.init(
                context.getBean(DocketReasonRepository.class),
                context.getBean(DocketReasonMapper.class),
                SearchFields.DOCKET_REASON,
                ResourceName.DOCKET_REASON
        ), DocketReasonRequest.class);
      
        register("purchase-orders", purchaseOrderController, purchaseOrderService.init(
                context.getBean(PurchaseOrderRepository.class),
                context.getBean(PurchaseOrderMapper.class),
                SearchFields.PURCHASE_ORDER,
                ResourceName.PURCHASE_ORDER
        ), PurchaseOrderRequest.class);

        register("order-resources", orderResourceController, orderResourceService.init(
                context.getBean(OrderResourceRepository.class),
                context.getBean(OrderResourceMapper.class),
                SearchFields.ORDER_RESOURCE,
                ResourceName.ORDER_RESOURCE
        ), OrderResourceRequest.class);

        register("order-cancellation-reasons", orderCancellationReasonController, orderCancellationReasonService.init(
                context.getBean(OrderCancellationReasonRepository.class),
                context.getBean(OrderCancellationReasonMapper.class),
                SearchFields.ORDER_CANCELLATION_REASON,
                ResourceName.ORDER_CANCELLATION_REASON
        ), OrderCancellationReasonRequest.class);

        register("orders", orderController, orderService.init(
                context.getBean(OrderRepository.class),
                context.getBean(OrderMapper.class),
                SearchFields.ORDER,
                ResourceName.ORDER
        ), OrderRequest.class);

        register("rooms", roomController, roomService.init(
                context.getBean(RoomRepository.class),
                context.getBean(RoomMapper.class),
                SearchFields.ROOM,
                ResourceName.ROOM
        ), RoomRequest.class);

        

        register("payment-methods", paymentMethodController, paymentMethodService.init(
                context.getBean(PaymentMethodRepository.class),
                context.getBean(PaymentMethodMapper.class),
                SearchFields.PAYMENT_METHOD,
                ResourceName.PAYMENT_METHOD
        ), PaymentMethodRequest.class);

        // Các đăng ký không sử dụng init
        register("provinces", provinceController, context.getBean(ProvinceService.class), ProvinceRequest.class);

        register("dockets", docketController, context.getBean(DocketService.class), DocketRequest.class);


        register("reviews", reviewController, context.getBean(ReviewService.class), ReviewRequest.class);

    }

    //requestType sẽ chuyển đổi dữ liệu từ JSON thành một đối tượng
    
    //Thực hiện đăng ký cho table với controller, service chung với kiểu dữ liệu của đối tượng yêu cầu mà controller sẽ xử lý
    private <D, R> void register(String resource,
                                 GenericController<D, R> controller,
                                 CrudService<Long, D, R> service,
                                 Class<D> requestType
    ) throws NoSuchMethodException {
        // Khởi tạo cấu hình để tùy chỉnh cách mà các URL mẫu được phân tích và ánh xạ đến các phương thức xử lý.
    	RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration();
        // Thiết lập parser để phân tích cú pháp các mẫu đường dẫn trong URL
    	options.setPatternParser(new PathPatternParser());
    	// Thiết lập controller cho cấu hình
        controller.setCrudService(service);
        //Thiết lập service cho cấu hình
        controller.setRequestType(requestType);

    	//Đăng ký API Restful với Spring's RequestMappingHandlerMapping
        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.GET)
                        //Trả về dữ liệu dưới dạng JSON
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("getAllResources", int.class, int.class,
                        String.class, String.class, String.class, boolean.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.GET)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("getResource", Long.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.POST)
                        //Xác định rằng phương thức này chỉ chấp nhận các yêu cầu có Content-Type là application/json
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("createResource", JsonNode.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.PUT)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("updateResource", Long.class, JsonNode.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.DELETE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("deleteResource", Long.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.DELETE)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("deleteResources", List.class)
        );
    }

}

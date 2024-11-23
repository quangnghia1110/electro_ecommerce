//Lớp này dùng để đăng ký tài liệu API (API documentation) cho các tài nguyên sử dụng GenericController
package hcmute.projectBackend2024.generic;

import org.springdoc.core.fn.builders.operation.Builder;
import org.springdoc.webmvc.core.fn.SpringdocRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.function.Consumer;

/**
 * Class này dùng để đăng ký tài liệu API cho các resource sử dụng GenericController
 */
@Configuration
public class GenericControllerDocumentationConfig {

	//xử lý các yêu cầu HTTP và trả về phản hồi HTTP với trạng thái "OK"
    HandlerFunction<ServerResponse> handler = request -> ServerResponse.ok().build();

    @Bean
    public RouterFunction<ServerResponse> route() {
        return generateRoute("provinces")
                .and(generateRoute("districts"))
                .and(generateRoute("wards"))
                .and(generateRoute("addresses"))
                .and(generateRoute("users"))
                .and(generateRoute("roles"))
                .and(generateRoute("offices"))
                .and(generateRoute("departments"))
                .and(generateRoute("job-types"))
                .and(generateRoute("job-titles"))
                .and(generateRoute("job-levels"))
                .and(generateRoute("employees"))
                .and(generateRoute("customer-groups"))
                .and(generateRoute("customer-resources"))
                .and(generateRoute("customer-status"))
                .and(generateRoute("customers"))
                .and(generateRoute("properties"))
                .and(generateRoute("categories"))
                .and(generateRoute("tags"))
                .and(generateRoute("guarantees"))
                .and(generateRoute("units"))
                .and(generateRoute("suppliers"))
                .and(generateRoute("brands"))
                .and(generateRoute("specifications"))
                .and(generateRoute("products"))
                .and(generateRoute("variants"))
                .and(generateRoute("images"))
                .and(generateRoute("product-inventory-limits"))
                .and(generateRoute("variant-inventory-limits"))
                .and(generateRoute("warehouses"))
                .and(generateRoute("counts"))
                .and(generateRoute("destinations"))
                .and(generateRoute("docket-reasons"))
                .and(generateRoute("transfers"))
                .and(generateRoute("dockets"))
                .and(generateRoute("storage-locations"))
                .and(generateRoute("purchase-orders"))
                .and(generateRoute("order-resources"))
                .and(generateRoute("order-cancellation-reasons"))
                .and(generateRoute("orders"))
                .and(generateRoute("waybills"))
                .and(generateRoute("reviews"))
                .and(generateRoute("payment-methods"))
                .and(generateRoute("promotions"))
                .and(generateRoute("rooms"))
                .and(generateRoute("reward-strategies"));
    }

    //Phương thức này tạo ra các tuyến đường (routes) cho các API tương ứng với các tài nguyên cụ thể
    private RouterFunction<ServerResponse> generateRoute(String resource) {
        return SpringdocRouteBuilder.route()
                //Định nghĩa tuyến đường, khi yêu cầu được gửi nó sẽ được xử lý bơi handler và được tài liệu hóa với phương thức trong genericController
        		.GET("/api/" + resource, handler, operation(resource, "getAllResources"))
                .GET("/api/" + resource + "/{id}", handler, operation(resource, "getResource"))
                .POST("/api/" + resource, handler, operation(resource, "createResource"))
                .PUT("/api/" + resource + "/{id}", handler, operation(resource, "updateResource"))
                .DELETE("/api/" + resource + "/{id}", handler, operation(resource, "deleteResource"))
                .DELETE("/api/" + resource, handler, operation(resource, "deleteResources"))
                .build();
    }

    //Phương thức này trả về một đối tượng Consumer<Builder>, được dùng để định nghĩa thông tin của một phương thức API cụ thể.
    private Consumer<Builder> operation(String resource, String handlerMethod) {
        return ops -> ops.operationId(resource).beanClass(GenericController.class).beanMethod(handlerMethod);
    }

}

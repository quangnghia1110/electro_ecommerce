package hcmute.projectBackend2024.dto.order;

import lombok.Data;
import org.springframework.lang.Nullable;


import java.time.Instant;

@Data
public class OrderResourceResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String code;
    private String name;
    private String color;
    private Integer status;
}

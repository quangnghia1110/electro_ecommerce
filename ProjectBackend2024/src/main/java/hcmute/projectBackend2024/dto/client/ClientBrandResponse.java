package hcmute.projectBackend2024.dto.client;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientBrandResponse {
    private Long brandId;
    private String brandName;
}

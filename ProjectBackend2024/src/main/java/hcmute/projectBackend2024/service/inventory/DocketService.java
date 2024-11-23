package hcmute.projectBackend2024.service.inventory;

import hcmute.projectBackend2024.dto.inventory.DocketRequest;
import hcmute.projectBackend2024.dto.inventory.DocketResponse;
import hcmute.projectBackend2024.generic.CrudService;

public interface DocketService extends CrudService<Long, DocketRequest, DocketResponse> {}

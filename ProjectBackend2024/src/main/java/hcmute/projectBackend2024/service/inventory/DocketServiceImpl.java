package hcmute.projectBackend2024.service.inventory;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.constant.ResourceName;
import hcmute.projectBackend2024.constant.SearchFields;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.dto.inventory.DocketRequest;
import hcmute.projectBackend2024.dto.inventory.DocketResponse;
import hcmute.projectBackend2024.entity.general.Notification;
import hcmute.projectBackend2024.entity.general.NotificationType;
import hcmute.projectBackend2024.entity.inventory.Docket;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.mapper.general.NotificationMapper;
import hcmute.projectBackend2024.mapper.inventory.DocketMapper;
import hcmute.projectBackend2024.repository.general.NotificationRepository;
import hcmute.projectBackend2024.repository.inventory.DocketRepository;
import hcmute.projectBackend2024.service.general.NotificationService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DocketServiceImpl implements DocketService {

    private DocketRepository docketRepository;

    private DocketMapper docketMapper;

    private NotificationRepository notificationRepository;

    private NotificationService notificationService;

    private NotificationMapper notificationMapper;

    @Override
    public ListResponse<DocketResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.DOCKET, docketRepository, docketMapper);
    }

    @Override
    public DocketResponse findById(Long id) {
        return defaultFindById(id, docketRepository, docketMapper, ResourceName.DOCKET);
    }

    @Override
    public DocketResponse save(DocketRequest request) {
        Docket docket = docketRepository.save(docketMapper.requestToEntity(request));
        return docketMapper.entityToResponse(docket);
    }

    @Override
    public DocketResponse save(Long id, DocketRequest request) {
        Docket docket = docketRepository.findById(id)
                .map(existingEntity -> docketMapper.partialUpdate(existingEntity, request))
                .map(docketRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DOCKET, FieldName.ID, id));
        return docketMapper.entityToResponse(docket);
    }

   
    @Override
    public void delete(Long id) {
        docketRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        docketRepository.deleteAllById(ids);
    }

}

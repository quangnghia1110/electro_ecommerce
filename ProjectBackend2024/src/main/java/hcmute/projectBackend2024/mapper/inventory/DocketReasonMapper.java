package hcmute.projectBackend2024.mapper.inventory;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.inventory.DocketReasonRequest;
import hcmute.projectBackend2024.dto.inventory.DocketReasonResponse;
import hcmute.projectBackend2024.entity.inventory.DocketReason;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocketReasonMapper extends GenericMapper<DocketReason, DocketReasonRequest, DocketReasonResponse> {}

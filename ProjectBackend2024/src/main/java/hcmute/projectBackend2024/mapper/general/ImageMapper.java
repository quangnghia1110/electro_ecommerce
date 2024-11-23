package hcmute.projectBackend2024.mapper.general;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.general.ImageRequest;
import hcmute.projectBackend2024.dto.general.ImageResponse;
import hcmute.projectBackend2024.entity.general.Image;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper extends GenericMapper<Image, ImageRequest, ImageResponse> {}

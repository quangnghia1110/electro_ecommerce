package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.TagRequest;
import hcmute.projectBackend2024.dto.product.TagResponse;
import hcmute.projectBackend2024.entity.product.Tag;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends GenericMapper<Tag, TagRequest, TagResponse> {
}

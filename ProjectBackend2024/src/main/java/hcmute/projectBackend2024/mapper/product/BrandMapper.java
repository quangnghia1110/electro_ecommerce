package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.BrandRequest;
import hcmute.projectBackend2024.dto.product.BrandResponse;
import hcmute.projectBackend2024.entity.product.Brand;
import hcmute.projectBackend2024.generic.GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper extends GenericMapper<Brand, BrandRequest, BrandResponse> {}

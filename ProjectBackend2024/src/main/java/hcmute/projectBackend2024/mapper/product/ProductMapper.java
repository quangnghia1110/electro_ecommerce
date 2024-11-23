package hcmute.projectBackend2024.mapper.product;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import hcmute.projectBackend2024.dto.product.ProductRequest;
import hcmute.projectBackend2024.dto.product.ProductResponse;
import hcmute.projectBackend2024.entity.product.Product;
import hcmute.projectBackend2024.generic.GenericMapper;
import hcmute.projectBackend2024.mapper.general.ImageMapper;
import hcmute.projectBackend2024.utils.MapperUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MapperUtils.class, ImageMapper.class, BrandMapper.class, SupplierMapper.class, UnitMapper.class,
                GuaranteeMapper.class})
public interface ProductMapper extends GenericMapper<Product, ProductRequest, ProductResponse> {

    @Override
    @BeanMapping(qualifiedByName = "attachProduct")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "unitId", target = "unit")
    @Mapping(source = "guaranteeId", target = "guarantee")
    Product requestToEntity(ProductRequest request);

    @Override
    @BeanMapping(qualifiedByName = "attachProduct")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "unitId", target = "unit")
    @Mapping(source = "guaranteeId", target = "guarantee")
    Product partialUpdate(@MappingTarget Product entity, ProductRequest request);

}

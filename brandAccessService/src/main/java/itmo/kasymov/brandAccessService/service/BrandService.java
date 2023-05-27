package itmo.kasymov.brandAccessService.service;

import itmo.kasymov.mainService.dto.BrandDto;

import java.util.List;

public interface BrandService {
    List<BrandDto> getAllBrands();

    List<BrandDto> getByBrandName(String name);

    BrandDto getBrandById(Long id) throws Exception;

    void saveBrand(BrandDto brandDto) throws Exception;

    void updateBrand(BrandDto brandDto) throws Exception;

    void deleteBrand(Long id) throws Exception;
}

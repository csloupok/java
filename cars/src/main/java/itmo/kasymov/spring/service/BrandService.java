package itmo.kasymov.spring.service;

import itmo.kasymov.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();

    List<Brand> getByBrandName(String name);

    Brand getBrandById(Long id) throws Exception;

    Brand createBrand(Brand brand);

    Brand updateBrand(Long id, Brand newBrand) throws Exception;

    void deleteBrand(Long id) throws Exception;
}

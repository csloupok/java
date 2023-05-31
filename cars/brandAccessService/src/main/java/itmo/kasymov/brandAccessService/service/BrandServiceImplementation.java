package itmo.kasymov.brandAccessService.service;

import itmo.kasymov.dto.BrandDto;
import itmo.kasymov.entity.Brand;
import itmo.kasymov.spring.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImplementation implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImplementation(BrandRepository brandRepository) throws Exception {
        if (brandRepository == null)
            throw new Exception("Repository is null.");
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll().stream().map(Brand::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BrandDto> getByBrandName(String name) {
        return brandRepository.getAllByName(name).stream().map(Brand::convertToDto).collect(Collectors.toList());
    }

    @Override
    public BrandDto getBrandById(Long id) throws Exception {
        return brandRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found")).convertToDto();
    }

    @Override
    public void saveBrand(BrandDto brandDto) throws Exception {
       brandRepository.save(brandDto.convertToEntity());
    }

    @Override
    public void updateBrand(BrandDto brandDto) throws Exception {
        Brand existingBrand = brandRepository.findById(brandDto.getId())
                .orElseThrow(() -> new Exception("Not found"));
        existingBrand.setName(brandDto.getName());
        brandRepository.save(existingBrand);
    }

    @Override
    public void deleteBrand(Long id) throws Exception {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        brandRepository.delete(existingBrand);
    }
}

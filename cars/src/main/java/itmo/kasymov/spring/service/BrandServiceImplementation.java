package itmo.kasymov.spring.service;

import itmo.kasymov.entity.Brand;
import itmo.kasymov.spring.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImplementation implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImplementation(BrandRepository brandRepository) throws Exception {
        if (brandRepository == null)
            throw new Exception("Repository is null.");
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getByBrandName(String name) {
        return brandRepository.getAllByName(name);
    }

    @Override
    public Brand getBrandById(Long id) throws Exception {
        return brandRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand newBrand) throws Exception {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        existingBrand.setName(newBrand.getName());
        return brandRepository.save(existingBrand);
    }

    @Override
    public void deleteBrand(Long id) throws Exception {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        brandRepository.delete(existingBrand);
    }
}

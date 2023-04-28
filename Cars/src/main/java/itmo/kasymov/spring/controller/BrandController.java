package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.model.Brand;
import itmo.kasymov.spring.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Operation(summary = "Get all brands")
    @RequestMapping(value = "/getAllBrands", method = RequestMethod.GET)
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Operation(summary = "Get by brand name")
    @RequestMapping(value = "/getByBrandName", method = RequestMethod.GET)
    public List<Brand> getByBrandName(String name) {
        return brandRepository.getAllByName(name);
    }

    @Operation(summary = "Get brand by id")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
    public Brand getBrandById(@PathVariable Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create brand")
    @RequestMapping(value = "/brand", method = RequestMethod.POST)
    public Brand createBrand(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    @Operation(summary = "Update brand")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.PUT)
    public Brand updateBrand(@PathVariable Long id, @RequestBody Brand brand) throws Exception {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingBrand.setName(brand.getName());
        // другие поля для обновления
        return brandRepository.save(existingBrand);
    }

    @Operation(summary = "Delete brand")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
    public void deleteBrand(@PathVariable Long id) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        brandRepository.delete(existingBrand);
    }

}

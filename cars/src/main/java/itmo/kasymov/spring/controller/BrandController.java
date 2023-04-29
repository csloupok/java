package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.entity.Brand;
import itmo.kasymov.spring.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "Get all brands")
    @RequestMapping(value = "/getAllBrands", method = RequestMethod.GET)
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @Operation(summary = "Get by brand name")
    @RequestMapping(value = "/getByBrandName", method = RequestMethod.GET)
    public List<Brand> getByBrandName(String name) {
        return brandService.getByBrandName(name);
    }

    @Operation(summary = "Get brand by id")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
    public Brand getBrandById(@PathVariable Long id) {
        try {
            return brandService.getBrandById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create brand")
    @RequestMapping(value = "/brand", method = RequestMethod.POST)
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }

    @Operation(summary = "Update brand")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.PUT)
    public Brand updateBrand(@PathVariable Long id, @RequestBody Brand brand) throws Exception {
        try {
            return brandService.updateBrand(id, brand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete brand")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
    public void deleteBrand(@PathVariable Long id) {
        try {
            brandService.deleteBrand(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
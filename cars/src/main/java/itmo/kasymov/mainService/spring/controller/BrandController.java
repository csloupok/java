package itmo.kasymov.mainService.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.mainService.dto.BrandDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController {
    private final RabbitTemplate rabbitTemplate;

    public BrandController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Operation(summary = "Get all brands")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/brand/getAllBrands", method = RequestMethod.GET)
    public List<BrandDto> getAllBrands() {
        return (List<BrandDto>) rabbitTemplate.convertSendAndReceive("getAllBrandsQueue");
    }

    @Operation(summary = "Get by brand name")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/brand/getByBrandName", method = RequestMethod.GET)
    public List<BrandDto> getByBrandName(String name) {
        return (List<BrandDto>) rabbitTemplate.convertSendAndReceive("getBrandByNameQueue");
    }

    @Operation(summary = "Get brand by id")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
    public BrandDto getBrandById(@PathVariable Long id) {
        try {
            return (BrandDto) rabbitTemplate.convertSendAndReceive("getBrandByIdQueue", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create brand")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/brand", method = RequestMethod.POST)
    public void createBrand(@RequestBody BrandDto brandDto) {
        rabbitTemplate.convertAndSend("addOwnerQueue", brandDto);
    }

    @Operation(summary = "Update brand")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/brand/update", method = RequestMethod.PUT)
    public void updateBrand(@RequestBody BrandDto brandDto) {
        try {
            rabbitTemplate.convertAndSend("updateOwnerQueue", brandDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete brand")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
    public void deleteBrand(@PathVariable Long id) {
        try {
            rabbitTemplate.convertAndSend("deleteOwnerQueue", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

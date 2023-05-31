package itmo.kasymov.brandAccessService.listener;

import itmo.kasymov.brandAccessService.service.BrandService;
import itmo.kasymov.dto.BrandDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandListener {
    final BrandService brandService;

    public BrandListener(BrandService brandService) {
        this.brandService = brandService;
    }

    @RabbitListener(queues = "addBrandQueue")
    public void save(@Payload BrandDto brandDto) throws Exception {
        brandService.saveBrand(brandDto);
    }


    @RabbitListener(queues = "getBrandByNameQueue")
    public List<BrandDto> getByName(@Payload String name) {
        return brandService.getByBrandName(name);
    }

    @RabbitListener(queues = "getBrandByIdQueue")
    public BrandDto getById(@Payload Long id) throws Exception {
        return brandService.getBrandById(id);
    }

    @RabbitListener(queues = "getAllBrandsQueue")
    public List<BrandDto> getAll() {
        return brandService.getAllBrands();
    }

    @RabbitListener(queues = "deleteBrandQueue")
    public void deleteById(@Payload Long id) throws Exception {
        brandService.deleteBrand(id);
    }

    @RabbitListener(queues = "updateOwnerQueue")
    public void update(@Payload BrandDto brandDto) throws Exception {
        brandService.updateBrand(brandDto);
    }
}

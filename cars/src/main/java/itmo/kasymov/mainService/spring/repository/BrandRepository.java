package itmo.kasymov.mainService.spring.repository;

import itmo.kasymov.mainService.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> getAllByName(String name);
}
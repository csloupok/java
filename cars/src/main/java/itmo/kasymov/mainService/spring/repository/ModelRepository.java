package itmo.kasymov.mainService.spring.repository;

import itmo.kasymov.mainService.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> getAllByBrand_Id(Long id);
    List<Model> getAllByName(String name);
}
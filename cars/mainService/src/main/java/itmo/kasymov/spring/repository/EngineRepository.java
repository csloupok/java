package itmo.kasymov.spring.repository;

import itmo.kasymov.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    List<Engine> getAllByModel_Id(Long id);
    List<Engine> getAllByName(String name);
}
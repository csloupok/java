package itmo.kasymov.spring.service;

import itmo.kasymov.entity.Engine;

import java.util.List;

public interface EngineService {
    List<Engine> getAllEngines();

    List<Engine> getByEngineName(String name);

    Engine getEngineById(Long id) throws Exception;

    Engine createEngine(Engine brand);

    Engine updateEngine(Long id, Engine brand) throws Exception;

    void deleteEngine(Long id) throws Exception;

    List<Engine> getAllByModelId(Long id);
}

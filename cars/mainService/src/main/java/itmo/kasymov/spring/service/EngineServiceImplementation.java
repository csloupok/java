package itmo.kasymov.spring.service;

import itmo.kasymov.entity.Engine;
import itmo.kasymov.spring.repository.EngineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImplementation implements EngineService {
    private final EngineRepository engineRepository;

    public EngineServiceImplementation(EngineRepository engineRepository) throws Exception {
        if (engineRepository == null)
            throw new Exception("Repository is null.");
        this.engineRepository = engineRepository;
    }

    @Override
    public List<Engine> getAllEngines() {
        return engineRepository.findAll();
    }

    @Override
    public List<Engine> getByEngineName(String name) {
        return engineRepository.getAllByName(name);
    }

    @Override
    public Engine getEngineById(Long id) throws Exception {
        return engineRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
    }

    @Override
    public Engine createEngine(Engine engine) {
        return engineRepository.save(engine);
    }

    @Override
    public Engine updateEngine(Long id, Engine newEngine) throws Exception {
        Engine existingEngine = engineRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        existingEngine.setName(newEngine.getName());
        existingEngine.setVolume(newEngine.getVolume());
        existingEngine.setCylinders(newEngine.getCylinders());
        existingEngine.setHeight(newEngine.getHeight());
        return engineRepository.save(existingEngine);
    }

    @Override
    public void deleteEngine(Long id) throws Exception {
        Engine existingEngine = engineRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        engineRepository.delete(existingEngine);
    }

    @Override
    public List<Engine> getAllByModelId(Long id) {
        return engineRepository.getAllByModel_Id(id);
    }
}
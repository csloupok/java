package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.entity.Engine;
import itmo.kasymov.spring.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EngineController {

    @Autowired
    private EngineRepository engineRepository;

    @Operation(summary = "Get all engines")
    @RequestMapping(value = "/getAllEngines", method = RequestMethod.GET)
    public List<Engine> getAllEngines() {
        return engineRepository.findAll();
    }

    @Operation(summary = "Get by engine name")
    @RequestMapping(value = "/getByEngineName", method = RequestMethod.GET)
    public List<Engine> getByEngineName(String name) {
        return engineRepository.getAllByName(name);
    }

    @Operation(summary = "Get engines by model id")
    @RequestMapping(value = "/getByModelId", method = RequestMethod.GET)
    public List<Engine> getAllByModelId(Long modelId) {
        return engineRepository.getAllByModel_Id(modelId);
    }

    @Operation(summary = "Get engine by id")
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.GET)
    public Engine getEngineById(@PathVariable Long id) {
        return engineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Save engine")
    @RequestMapping(value = "/engine", method = RequestMethod.POST)
    public Engine createEngine(@RequestBody Engine engine) {
        return engineRepository.save(engine);
    }

    @Operation(summary = "Update engine")
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.PUT)
    public Engine updateEngine(@PathVariable Long id, @RequestBody Engine engine) throws Exception {
        Engine existingEngine = engineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingEngine.setName(engine.getName());
        existingEngine.setCylinders(engine.getCylinders());
        existingEngine.setHeight(engine.getHeight());
        existingEngine.setVolume(engine.getVolume());
        // другие поля для обновления
        return engineRepository.save(existingEngine);
    }

    @Operation(summary = "Delete engine")
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.DELETE)
    public void deleteEngine(@PathVariable Long id) {
        Engine existingEngine = engineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        engineRepository.delete(existingEngine);
    }
}
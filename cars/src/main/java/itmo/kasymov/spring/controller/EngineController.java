package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.entity.Engine;
import itmo.kasymov.spring.service.EngineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EngineController {

    private final EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @Operation(summary = "Get all engines")
    @RequestMapping(value = "/getAllEngines", method = RequestMethod.GET)
    public List<Engine> getAllEngines() {
        return engineService.getAllEngines();
    }

    @Operation(summary = "Get by engine name")
    @RequestMapping(value = "/getByEngineName", method = RequestMethod.GET)
    public List<Engine> getByEngineName(String name) {
        return engineService.getByEngineName(name);
    }

    @Operation(summary = "Get engines by model id")
    @RequestMapping(value = "/getByModelId", method = RequestMethod.GET)
    public List<Engine> getAllByModelId(Long modelId) {
        return engineService.getAllByModelId(modelId);
    }

    @Operation(summary = "Get engine by id")
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.GET)
    public Engine getEngineById(@PathVariable Long id) {
        try {
            return engineService.getEngineById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Save engine")
    @RequestMapping(value = "/engine", method = RequestMethod.POST)
    public Engine createEngine(@RequestBody Engine engine) {
        return engineService.createEngine(engine);
    }

    @Operation(summary = "Update engine")
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.PUT)
    public Engine updateEngine(@PathVariable Long id, @RequestBody Engine newEngine) throws Exception {
        try {
            return engineService.updateEngine(id, newEngine);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete engine")
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.DELETE)
    public void deleteEngine(@PathVariable Long id) {
        try {
            engineService.deleteEngine(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
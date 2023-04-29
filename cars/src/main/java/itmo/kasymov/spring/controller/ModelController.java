package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.entity.Model;
import itmo.kasymov.spring.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Operation(summary = "Get all models")
    @RequestMapping(value = "/getAllModels", method = RequestMethod.GET)
    public List<Model> getAllModels() {
        return modelService.getAllModels();
    }

    @Operation(summary = "Get by model name")
    @RequestMapping(value = "/getByModelName", method = RequestMethod.GET)
    public List<Model> getByModelName(String name) {
        return modelService.getByModelName(name);
    }

    @Operation(summary = "Get models by brand id")
    @RequestMapping(value = "/getByBrandId", method = RequestMethod.GET)
    public List<Model> getAllByBrandId(Long brandId) {
        return modelService.getAllByBrandId(brandId);
    }

    @Operation(summary = "Get model by id")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
    public Model getModelById(@PathVariable Long id) {
        try {
            return modelService.getModelById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Save model")
    @RequestMapping(value = "/model", method = RequestMethod.POST)
    public Model createModel(@RequestBody Model model) {
        return modelService.createModel(model);
    }

    @Operation(summary = "Update model")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.PUT)
    public Model updateModel(@PathVariable Long id, @RequestBody Model newModel) throws Exception {
        try {
            return modelService.updateModel(id, newModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete model")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public void deleteModel(@PathVariable Long id) {
        try {
            modelService.deleteModel(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

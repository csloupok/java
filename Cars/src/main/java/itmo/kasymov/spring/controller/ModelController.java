package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.model.Model;
import itmo.kasymov.spring.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @Operation(summary = "Get all models")
    @RequestMapping(value = "/getAllModels", method = RequestMethod.GET)
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Operation(summary = "Get by model name")
    @RequestMapping(value = "/getByModelName", method = RequestMethod.GET)
    public List<Model> getByModelName(String name) {
        return modelRepository.getAllByName(name);
    }

    @Operation(summary = "Get models by brand id")
    @RequestMapping(value = "/getByBrandId", method = RequestMethod.GET)
    public List<Model> getAllByBrandId(Long brandId) {
        return modelRepository.getAllByBrand_Id(brandId);
    }

    @Operation(summary = "Get model by id")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
    public Model getModelById(@PathVariable Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Save model")
    @RequestMapping(value = "/model", method = RequestMethod.POST)
    public Model createModel(@RequestBody Model model) {
        return modelRepository.save(model);
    }

    @Operation(summary = "Update model")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.PUT)
    public Model updateModel(@PathVariable Long id, @RequestBody Model model) throws Exception {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingModel.setName(model.getName());
        existingModel.setBrand(model.getBrand());
        // другие поля для обновления
        return modelRepository.save(existingModel);
    }

    @Operation(summary = "Delete model")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public void deleteModel(@PathVariable Long id) {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelRepository.delete(existingModel);
    }
}

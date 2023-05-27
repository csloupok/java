package itmo.kasymov.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.entity.Model;
import itmo.kasymov.entity.User;
import itmo.kasymov.spring.service.ModelService;
import itmo.kasymov.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
public class ModelController {

    private final ModelService modelService;
    private final UserService userService;

    public ModelController(ModelService modelService, UserService userService) {
        this.modelService = modelService;
        this.userService = userService;
    }

    @Operation(summary = "Get all models")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model/getAllModels", method = RequestMethod.GET)
    public List<Model> getAllModels() {
        return modelService.getAllModels();
    }

    @Operation(summary = "Get by model name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/model/getByModelName/{name}", method = RequestMethod.GET)
    public List<Model> getByModelName(@PathVariable String name) {
        List<Model> models = modelService.getByModelName(name);
        models.removeIf(m -> !Objects.equals(m.getBrand().getId(), getCurrentUser().getBrand().getId()));
        return models;
    }


    @Operation(summary = "Get models by brand id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/model/getByBrandId/{brandId}", method = RequestMethod.GET)
    public List<Model> getAllByBrandId(@PathVariable Long brandId) {
        if (Objects.equals(brandId, getCurrentUser().getBrand().getId())) {
            return modelService.getAllByBrandId(brandId);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Get model by id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
    public Model getModelById(@PathVariable Long id) {
        try {
            Model model = modelService.getModelById(id);
            if (Objects.equals(model.getBrand().getId(), getCurrentUser().getBrand().getId())) {
                return model;
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Save model")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model", method = RequestMethod.POST)
    public Model createModel(@RequestBody Model model) {
        return modelService.createModel(model);
    }

    @Operation(summary = "Update model")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.PUT)
    public Model updateModel(@PathVariable Long id, @RequestBody Model newModel) {
        try {
            return modelService.updateModel(id, newModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete model")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public void deleteModel(@PathVariable Long id) {
        try {
            modelService.deleteModel(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserByUsername(username);
    }
}

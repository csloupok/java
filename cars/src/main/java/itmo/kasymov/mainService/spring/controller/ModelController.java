package itmo.kasymov.mainService.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.kasymov.mainService.dto.ModelDto;
import itmo.kasymov.mainService.entity.User;
import itmo.kasymov.mainService.spring.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;

    public ModelController(UserService userService, RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Operation(summary = "Get all models")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model/getAllModels", method = RequestMethod.GET)
    public List<ModelDto> getAllModels() {
        return (List<ModelDto>) rabbitTemplate.convertSendAndReceive("getAllQueue");
    }

    @Operation(summary = "Get by model name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/model/getByModelName/{name}", method = RequestMethod.GET)
    public List<ModelDto> getByModelName(@PathVariable String name) {
        List<ModelDto> models = (List<ModelDto>) rabbitTemplate.convertSendAndReceive("getByNameQueue");
        models.removeIf(m -> !Objects.equals(m.getBrand().getId(), getCurrentUser().getBrand().getId()));
        return models;
    }


    @Operation(summary = "Get models by brand id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/model/getByBrandId/{brandId}", method = RequestMethod.GET)
    public List<ModelDto> getAllByBrandId(@PathVariable Long brandId) {
        if (Objects.equals(brandId, getCurrentUser().getBrand().getId())) {
            return (List<ModelDto>) rabbitTemplate.convertSendAndReceive("getAllByBrandIdQueue");
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Get model by id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
    public ModelDto getModelById(@PathVariable Long id) {
        try {
            ModelDto modelDto = (ModelDto) rabbitTemplate.convertSendAndReceive("getByIdQueue");
            if (Objects.equals(modelDto.getBrand().getId(), getCurrentUser().getBrand().getId())) {
                return modelDto;
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
    public void createModel(@RequestBody ModelDto modelDto) {
        rabbitTemplate.convertSendAndReceive("addQueue");
    }

    @Operation(summary = "Update model")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model/update", method = RequestMethod.PUT)
    public void updateModel(@RequestBody ModelDto modelDto) {
        try {
            rabbitTemplate.convertSendAndReceive("updateQueue");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete model")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public void deleteModel(@PathVariable Long id) {
        try {
            rabbitTemplate.convertSendAndReceive("deleteQueue");
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

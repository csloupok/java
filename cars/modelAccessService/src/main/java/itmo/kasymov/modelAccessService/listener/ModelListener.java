package itmo.kasymov.modelAccessService.listener;

import itmo.kasymov.dto.ModelDto;
import itmo.kasymov.modelAccessService.service.ModelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelListener {
    final ModelService modelService;

    public ModelListener(ModelService modelService) {
        this.modelService = modelService;
    }

    @RabbitListener(queues = "addQueue")
    public void save(@Payload ModelDto modelDto) throws Exception {
        modelService.saveModel(modelDto);
    }

    @RabbitListener(queues = "getByIdQueue")
    public ModelDto getById(@Payload Long id) throws Exception {
        return modelService.getModelById(id);
    }

    @RabbitListener(queues = "getAllQueue")
    public List<ModelDto> getAll() {
        return modelService.getAllModels();
    }

    @RabbitListener(queues = "getByNameQueue")
    public List<ModelDto> getByName(@Payload String name) {
        return modelService.getByModelName(name);
    }

    @RabbitListener(queues = "getAllByBrandIdQueue")
    public List<ModelDto> getAllByBrandId(@Payload Long id) {
        return modelService.getAllByBrandId(id);
    }

    @RabbitListener(queues = "deleteQueue")
    public void deleteById(@Payload Long id) throws Exception {
       modelService.deleteModel(id);
    }

    @RabbitListener(queues = "updateQueue")
    public void updateCat(@Payload ModelDto modelDto) throws Exception {
        modelService.updateModel(modelDto);
    }
}

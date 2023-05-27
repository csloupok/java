package itmo.kasymov.modelAccessService.service;

import itmo.kasymov.mainService.dto.ModelDto;

import java.util.List;

public interface ModelService {
    List<ModelDto> getAllModels();

    List<ModelDto> getByModelName(String name);

    ModelDto getModelById(Long id) throws Exception;

    void saveModel(ModelDto modelDto) throws Exception;

    void updateModel(ModelDto modelDto) throws Exception;

    void deleteModel(Long id) throws Exception;

    List<ModelDto> getAllByBrandId(Long id);
}

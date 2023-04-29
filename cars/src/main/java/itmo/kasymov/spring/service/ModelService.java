package itmo.kasymov.spring.service;

import itmo.kasymov.entity.Model;

import java.util.List;

public interface ModelService {
    List<Model> getAllModels();

    List<Model> getByModelName(String name);

    Model getModelById(Long id) throws Exception;

    Model createModel(Model model);

    Model updateModel(Long id, Model newModel) throws Exception;

    void deleteModel(Long id) throws Exception;

    List<Model> getAllByBrandId(Long id);
}

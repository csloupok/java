package itmo.kasymov.spring.service;

import itmo.kasymov.entity.Model;
import itmo.kasymov.spring.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImplementation implements ModelService {
    private final ModelRepository modelRepository;

    public ModelServiceImplementation(ModelRepository modelRepository) throws Exception {
        if (modelRepository == null)
            throw new Exception("Repository is null.");
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> getByModelName(String name) {
        return modelRepository.getAllByName(name);
    }

    @Override
    public Model getModelById(Long id) throws Exception {
        return modelRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
    }

    @Override
    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Model updateModel(Long id, Model newModel) throws Exception {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        existingModel.setName(newModel.getName());
        return modelRepository.save(existingModel);
    }

    @Override
    public void deleteModel(Long id) throws Exception {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        modelRepository.delete(existingModel);
    }

    @Override
    public List<Model> getAllByBrandId(Long id) {
        return modelRepository.getAllByBrand_Id(id);
    }
}
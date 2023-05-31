package itmo.kasymov.modelAccessService.service;

import itmo.kasymov.dto.ModelDto;
import itmo.kasymov.entity.Model;
import itmo.kasymov.spring.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImplementation implements ModelService {
    private final ModelRepository modelRepository;

    public ModelServiceImplementation(ModelRepository modelRepository) throws Exception {
        if (modelRepository == null)
            throw new Exception("Repository is null.");
        this.modelRepository = modelRepository;
    }

    @Override
    public List<ModelDto> getAllModels() {
        return modelRepository.findAll().stream().map(Model::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getByModelName(String name) {
        return modelRepository.getAllByName(name).stream().map(Model::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ModelDto getModelById(Long id) throws Exception {
        return modelRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found")).convertToDto();
    }

    @Override
    public void saveModel(ModelDto modelDto) throws Exception {
        modelRepository.save(modelDto.convertToEntity());
    }

    @Override
    public void updateModel(ModelDto modelDto) throws Exception {
        Model existingModel = modelRepository.findById(modelDto.getId())
                .orElseThrow(() -> new Exception("Not found"));
        existingModel.setName(modelDto.getName());
        modelRepository.save(existingModel);
    }

    @Override
    public void deleteModel(Long id) throws Exception {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        modelRepository.delete(existingModel);
    }

    @Override
    public List<ModelDto> getAllByBrandId(Long id) {
        return modelRepository.getAllByBrand_Id(id).stream().map(Model::convertToDto).collect(Collectors.toList());
    }
}
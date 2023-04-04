package itmo.kasymov.dao.mybatis;

import itmo.kasymov.model.Model;

import java.util.List;

public interface ModelMapper {
    void save(Model var1);

    void deleteById(Long var1);

    void deleteByEntity(Model var1);

    void deleteAll();

    void update(Model var1);

    Model getById(Long var1);

    List<Model> getAll();

    List<Model> getAllByBrandId(Long var1);
}

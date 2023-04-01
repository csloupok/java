package itmo.kasymov.dao.mybatis;

import itmo.kasymov.model.Model;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MybatisModelDao extends MybatisDao<Model> {
    public MybatisModelDao() {
    }

    public void save(Model entity) {
        this.doActionDb((statement) -> statement.getMapper(ModelMapper.class).save(entity));
    }

    public void deleteById(long id) {
        this.doActionDb((statement) -> statement.getMapper(ModelMapper.class).deleteById(id));
    }

    public void deleteByEntity(Model entity) {
        this.doActionDb((statement) -> statement.getMapper(ModelMapper.class).deleteByEntity(entity));
    }

    public void deleteAll() {
        this.doActionDb((statement) -> statement.getMapper(ModelMapper.class).deleteAll());
    }

    public void update(Model entity) {
        this.doActionDb((statement) -> statement.getMapper(ModelMapper.class).update(entity));
    }

    public Model getById(long id) {
        AtomicReference<Model> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> result.set(statement.getMapper(ModelMapper.class).getById(id)));
        return result.get();
    }

    public List<Model> getAll() {
        AtomicReference<List<Model>> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> result.set(statement.getMapper(ModelMapper.class).getAll()));
        return result.get();
    }

    public List<Model> getAllByBrandId(long id) {
        AtomicReference<List<Model>> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> result.set(statement.getMapper(ModelMapper.class).getAllByBrandId(id)));
        return result.get();
    }
}

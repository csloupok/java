package itmo.kasymov.dao.mybatis;

import itmo.kasymov.entity.Brand;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MybatisBrandDao extends MybatisDao<Brand> {
    public MybatisBrandDao() {
    }

    public void save(Brand entity) {
        this.doActionDb((statement) -> statement.getMapper(BrandMapper.class).save(entity));
    }

    public void deleteById(Long id) {
        this.doActionDb((statement) -> statement.getMapper(BrandMapper.class).deleteById(id));
    }

    public void deleteByEntity(Brand entity) {
        this.doActionDb((statement) -> statement.getMapper(BrandMapper.class).deleteByEntity(entity));
    }

    public void deleteAll() {
        this.doActionDb((statement) -> statement.getMapper(BrandMapper.class).deleteAll());
    }

    public void update(Brand entity) {
        this.doActionDb((statement) -> statement.getMapper(BrandMapper.class).update(entity));
    }

    public Brand getById(Long id) {
        AtomicReference<Brand> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> result.set(statement.getMapper(BrandMapper.class).getById(id)));
        return result.get();
    }

    public List<Brand> getAll() {
        AtomicReference<List<Brand>> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> result.set(statement.getMapper(BrandMapper.class).getAll()));
        return result.get();
    }
}

package itmo.kasymov.dao.mybatis;

import itmo.kasymov.model.Brand;

import java.util.List;

public interface BrandMapper {
    void save(Brand var1);

    void deleteById(long var1);

    void deleteByEntity(Brand var1);

    void deleteAll();

    void update(Brand var1);

    Brand getById(long var1);

    List<Brand> getAll();
}

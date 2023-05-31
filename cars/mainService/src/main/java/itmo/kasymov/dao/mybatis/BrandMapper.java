package itmo.kasymov.dao.mybatis;

import itmo.kasymov.entity.Brand;

import java.util.List;

public interface BrandMapper {
    void save(Brand var1);

    void deleteById(Long var1);

    void deleteByEntity(Brand var1);

    void deleteAll();

    void update(Brand var1);

    Brand getById(Long var1);

    List<Brand> getAll();
}

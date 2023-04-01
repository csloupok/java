//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package itmo.kasymov.dao.mybatis;

import itmo.kasymov.model.Model;

import java.util.List;

public interface ModelMapper {
    void save(Model var1);

    void deleteById(long var1);

    void deleteByEntity(Model var1);

    void deleteAll();

    void update(Model var1);

    Model getById(long var1);

    List<Model> getAll();

    List<Model> getAllByBrandId(long var1);
}

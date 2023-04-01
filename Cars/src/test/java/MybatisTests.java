//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import itmo.kasymov.dao.mybatis.MybatisBrandDao;
import itmo.kasymov.dao.mybatis.MybatisModelDao;
import itmo.kasymov.model.Brand;
import itmo.kasymov.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MybatisTests {
    private final MybatisModelDao modelDao = new MybatisModelDao();
    private final MybatisBrandDao brandDao = new MybatisBrandDao();

    public MybatisTests() {
    }

    @Test
    public void save() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        Assertions.assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> {
            return model.getId() == model1.getId();
        }));
        Assertions.assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> {
            return model.getId() == model2.getId();
        }));
        Assertions.assertTrue(this.brandDao.getAll().stream().anyMatch((brnd) -> {
            return brnd.getId() == brand.getId();
        }));
    }

    @Test
    public void removeByEntity() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        this.modelDao.deleteByEntity(model1);
        Assertions.assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().noneMatch((model) -> {
            return model.getId() == model1.getId();
        }));
        Assertions.assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> {
            return model.getId() == model2.getId();
        }));
        this.brandDao.deleteByEntity(brand);
        Assertions.assertTrue(this.brandDao.getAll().stream().noneMatch((brnd) -> {
            return brnd.getId() == brand.getId();
        }));
    }

    @Test
    public void removeById() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        this.modelDao.deleteById(model1.getId());
        Assertions.assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().noneMatch((model) -> {
            return model.getId() == model1.getId();
        }));
        Assertions.assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> {
            return model.getId() == model2.getId();
        }));
        this.brandDao.deleteById(brand.getId());
        Assertions.assertTrue(this.brandDao.getAll().stream().noneMatch((brnd) -> {
            return brnd.getId() == brand.getId();
        }));
    }

    @Test
    public void removeAll() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        this.modelDao.deleteAll();
        Assertions.assertTrue(this.modelDao.getAll().isEmpty());
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        this.brandDao.deleteAll();
        Assertions.assertTrue(this.modelDao.getAll().isEmpty());
        Assertions.assertTrue(this.brandDao.getAll().isEmpty());
    }

    @Test
    public void update() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        brand.setName("New Brand");
        model1.setName("New Model1");
        this.brandDao.update(brand);
        this.modelDao.update(model1);
        Assertions.assertEquals("New Brand", this.brandDao.getById(brand.getId()).getName());
        Assertions.assertEquals("New Model1", this.modelDao.getById(model1.getId()).getName());
    }

    @Test
    public void getById() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        Assertions.assertEquals(brand.getId(), this.brandDao.getById(brand.getId()).getId());
        Assertions.assertEquals(model1.getId(), this.modelDao.getById(model1.getId()).getId());
    }
}

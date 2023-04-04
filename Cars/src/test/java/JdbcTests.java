import itmo.kasymov.dao.jdbc.JdbcBrandDao;
import itmo.kasymov.dao.jdbc.JdbcModelDao;
import itmo.kasymov.model.Brand;
import itmo.kasymov.model.Model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JdbcTests {
    private final JdbcModelDao modelDao = new JdbcModelDao();
    private final JdbcBrandDao brandDao = new JdbcBrandDao();

    public JdbcTests() {
    }

    @Test
    public void save() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);

        assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> model.getId().equals(model1.getId())));
        assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> model.getId().equals(model2.getId())));
        assertTrue(this.brandDao.getAll().stream().anyMatch((brnd) -> brnd.getId().equals(brand.getId())));
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
        assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().noneMatch((model) -> model.getId().equals(model1.getId())));
        assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> model.getId().equals(model2.getId())));
        this.brandDao.deleteByEntity(brand);
        assertTrue(this.brandDao.getAll().stream().noneMatch((brnd) -> brnd.getId().equals(brand.getId())));
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
        assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().noneMatch((model) -> model.getId().equals(model1.getId())));
        assertTrue(this.modelDao.getAllByBrandId(brand.getId()).stream().anyMatch((model) -> model.getId().equals(model2.getId())));
        this.brandDao.deleteById(brand.getId());
        assertTrue(this.brandDao.getAll().stream().noneMatch((brnd) -> brnd.getId().equals(brand.getId())));
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
        assertTrue(this.modelDao.getAll().isEmpty());
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        this.brandDao.deleteAll();
        assertTrue(this.modelDao.getAll().isEmpty());
        assertTrue(this.brandDao.getAll().isEmpty());
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
        assertEquals("New Brand", this.brandDao.getById(brand.getId()).getName());
        assertEquals("New Model1", this.modelDao.getById(model1.getId()).getName());
    }

    @Test
    public void getById() throws Exception {
        Brand brand = new Brand("Brand", LocalDate.now());
        Model model1 = new Model("Model1", 100, 100, "HATCHBACK", brand.getId());
        Model model2 = new Model("Model2", 100, 100, "SEDAN", brand.getId());
        this.brandDao.save(brand);
        this.modelDao.save(model1);
        this.modelDao.save(model2);
        assertEquals(brand.getId(), this.brandDao.getById(brand.getId()).getId());
        assertEquals(model1.getId(), this.modelDao.getById(model1.getId()).getId());
    }
}

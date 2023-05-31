package itmo.kasymov.dao.jdbc;

import itmo.kasymov.entity.Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JdbcModelDao extends JdbcDao<Model> {
    public JdbcModelDao() {
    }

    public Model save(Model entity) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("INSERT INTO MODEL (MODEL_ID, MODEL_NAME, LENGTH, WIDTH, TYPE, BRAND) values (%d, '%s', %d, %d, '%s', %d)", entity.getId(), entity.getName(), entity.getLength(), entity.getWidth(), entity.getType(), entity.getBrandId())));
        return entity;
    }

    public void deleteById(Long id) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("DELETE FROM MODEL WHERE MODEL_ID = %d", id)));
    }

    public void deleteByEntity(Model entity) {
        this.deleteById(entity.getId());
    }

    public void deleteAll() {
        this.doActionDb((statement) -> statement.executeUpdate("TRUNCATE TABLE MODEL CASCADE"));
    }

    public Model update(Model entity) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("UPDATE MODEL SET MODEL_NAME = '%s', LENGTH = %d, WIDTH = %d, TYPE = '%s', BRAND = %d WHERE MODEL_ID = %d", entity.getName(), entity.getLength(), entity.getWidth(), entity.getType(), entity.getBrandId(), entity.getId())));
        return entity;
    }

    public Model getById(Long id) {
        AtomicReference<Model> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> {
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM MODEL WHERE MODEL_ID = %d", id));

            while (rs.next()) {
                result.set(new Model(rs.getString("MODEL_NAME"), rs.getInt("LENGTH"), rs.getInt("WIDTH"), rs.getString("TYPE"), rs.getLong("BRAND"), rs.getLong("MODEL_ID")));
            }

        });
        return result.get();
    }

    public List<Model> getAll() {
        AtomicReference<List<Model>> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> {
            ResultSet rs = statement.executeQuery("SELECT * FROM MODEL");
            readModels(result, rs);
        });
        return result.get();
    }

    private void readModels(AtomicReference<List<Model>> result, ResultSet rs) throws Exception {
        List<Model> models = new ArrayList<>();
        while (rs.next())
            models.add(new Model(rs.getString("MODEL_NAME"), rs.getInt("LENGTH"), rs.getInt("WIDTH"), rs.getString("TYPE"), rs.getLong("BRAND"), rs.getLong("MODEL_ID")));
        result.set(models);
    }

    public List<Model> getAllByBrandId(Long brand_id) {
        AtomicReference<List<Model>> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> {
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM MODEL WHERE BRAND = %d FETCH FIRST 5 ROWS ONLY", brand_id));
            readModels(result, rs);
        });
        return result.get();
    }
}

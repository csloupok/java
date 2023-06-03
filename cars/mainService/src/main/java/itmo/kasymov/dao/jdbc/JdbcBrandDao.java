package itmo.kasymov.dao.jdbc;

import itmo.kasymov.entity.Brand;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JdbcBrandDao extends JdbcDao<Brand> {
    public JdbcBrandDao() {
    }

    public Brand save(Brand entity) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("INSERT INTO BRAND (BRAND_ID, BRAND_NAME, FOUND_DATE) values (%d, '%s', '%s')", entity.getId(), entity.getName(), entity.getDate().toString())));
        return entity;
    }

    public void deleteById(Long id) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("DELETE FROM BRAND WHERE BRAND_ID = %d", id)));
    }

    public void deleteByEntity(Brand entity) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("DELETE FROM BRAND WHERE BRAND_NAME = '%s' AND FOUND_DATE = '%s'", entity.getName(), entity.getDate())));
    }

    public void deleteAll() {
        this.doActionDb((statement) -> statement.executeUpdate("TRUNCATE TABLE BRAND CASCADE"));
    }

    public Brand update(Brand entity) {
        this.doActionDb((statement) -> statement.executeUpdate(String.format("UPDATE BRAND SET BRAND_NAME = '%s', FOUND_DATE = '%s' WHERE BRAND_ID = %d", entity.getName(), entity.getDate(), entity.getId())));
        return entity;
    }

    public Brand getById(Long id) {
        AtomicReference<Brand> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> {
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM BRAND WHERE BRAND_ID = %d", id));

            while (rs.next()) {
                result.set(new Brand(rs.getString("BRAND_NAME"), rs.getDate("FOUND_DATE").toLocalDate(), rs.getLong("BRAND_ID")));
            }

        });
        return result.get();
    }

    public List<Brand> getAll() {
        AtomicReference<List<Brand>> result = new AtomicReference<>(null);
        this.doActionDb((statement) -> {
            ResultSet rs = statement.executeQuery("SELECT * FROM BRAND");
            List<Brand> brands = new ArrayList<>();

            while (rs.next()) {
                brands.add(new Brand(rs.getString("BRAND_NAME"), rs.getDate("FOUND_DATE").toLocalDate(), rs.getLong("BRAND_ID")));
            }

            result.set(brands);
        });
        return result.get();
    }
}

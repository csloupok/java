package itmo.kasymov;

import itmo.kasymov.entity.Brand;
import itmo.kasymov.spring.controller.BrandController;
import org.flywaydb.core.Flyway;

import java.util.List;

public class Migration {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost/eldarkasymov", "eldarkasymov", null).load();
        flyway.baseline();
        flyway.repair();
        flyway.migrate();
    }
}

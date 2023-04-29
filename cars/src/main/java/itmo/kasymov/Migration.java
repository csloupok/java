package itmo.kasymov;

import org.flywaydb.core.Flyway;

public class Migration {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost/eldarkasymov", "eldarkasymov", null).load();
        flyway.baseline();
        flyway.repair();
        flyway.migrate();
    }
}

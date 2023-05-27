package itmo.kasymov.mainService.dao.hibernate;

import itmo.kasymov.mainService.entity.Brand;
import itmo.kasymov.mainService.entity.Model;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.postgresql.Driver;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", Driver.class.getName());
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/eldarkasymov");
            configuration.setProperty("hibernate.connection.username", "eldarkasymov");
            configuration.setProperty("hibernate.connection.password", "");
            configuration.addAnnotatedClass(Brand.class);
            configuration.addAnnotatedClass(Model.class);
            ServiceRegistry serviceRegistry = (new StandardServiceRegistryBuilder()).applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable var2) {
            System.err.println("Initial SessionFactory creation failed." + var2);
            throw new ExceptionInInitializerError(var2);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
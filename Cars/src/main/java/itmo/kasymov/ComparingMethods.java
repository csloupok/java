package itmo.kasymov;

import itmo.kasymov.dao.hibernate.HibernateBrandDao;
import itmo.kasymov.dao.jdbc.JdbcBrandDao;
import itmo.kasymov.dao.mybatis.MybatisBrandDao;
import itmo.kasymov.model.Brand;

import java.time.LocalDate;

public class ComparingMethods {
    public ComparingMethods() {
    }

    public static void main(String[] args) throws Exception {
        JdbcBrandDao jdbcBrandDao = new JdbcBrandDao();
        long startTime = System.nanoTime();

        for (int i = 0; i < 100; ++i) {
            jdbcBrandDao.save(new Brand("Brand", LocalDate.now()));
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Total execution time to create and save 100 objects in JDBC: %d ms%n", elapsedTime / 1000000L);
        startTime = System.nanoTime();
        jdbcBrandDao.getAll();
        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Total execution time to get 100 objects in JDBC: %d ms%n", elapsedTime / 1000000L);
        HibernateBrandDao hibernateBrandDao = new HibernateBrandDao();
        startTime = System.nanoTime();

        for (int i = 0; i < 100; ++i) {
            hibernateBrandDao.save(new Brand("Brand", LocalDate.now()));
        }

        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Total execution time to create and save 100 objects in Hibernate: %d ms%n", elapsedTime / 1000000L);
        startTime = System.nanoTime();
        hibernateBrandDao.getAll();
        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Total execution time to get 100 objects in Hibernate: %d ms%n", elapsedTime / 1000000L);
        MybatisBrandDao mybatisBrandDao = new MybatisBrandDao();
        startTime = System.nanoTime();

        for (int i = 0; i < 100; ++i) {
            mybatisBrandDao.save(new Brand("Brand", LocalDate.now()));
        }

        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Total execution time to create and save 100 objects in MyBatis: %d ms%n", elapsedTime / 1000000L);
        startTime = System.nanoTime();
        mybatisBrandDao.getAll();
        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Total execution time to get 100 objects in MyBatis: %d ms%n", elapsedTime / 1000000L);
    }
}

package itmo.kasymov.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.function.Consumer;

public abstract class JdbcDao<T> {

    public JdbcDao() {
    }

    public abstract T save(T var1);

    public abstract void deleteById(Long var1);

    public abstract void deleteByEntity(T var1);

    public abstract void deleteAll();

    public abstract T update(T var1);

    public abstract T getById(Long var1);

    public abstract List<T> getAll();

    protected void doActionDb(ThrowingConsumer<Statement> consumer) {
        try {
            String dbURL = "jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:/data.sql'";
            Connection c = DriverManager.getConnection(dbURL, "sa", "password");


            try {
                Statement statement = c.createStatement();

                try {
                    consumer.accept(statement);
                } catch (Throwable var8) {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (Throwable var9) {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (c != null) {
                c.close();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }

    @FunctionalInterface
    public interface ThrowingConsumer<T> extends Consumer<T> {
        default void accept(T elem) {
            try {
                this.acceptThrows(elem);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }

        void acceptThrows(T var1) throws Exception;
    }
}

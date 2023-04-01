//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package itmo.kasymov.dao.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public abstract class MybatisDao<T> {
    public MybatisDao() {
    }

    public abstract void save(T var1);

    public abstract void deleteById(long var1);

    public abstract void deleteByEntity(T var1);

    public abstract void deleteAll();

    public abstract void update(T var1);

    public abstract T getById(long var1);

    public abstract List<T> getAll();

    protected void doActionDb(ThrowingConsumer<SqlSession> consumer) {
        try {
            SqlSessionFactory sqlSessionFactory = (new SqlSessionFactoryBuilder()).build(Resources.getResourceAsStream("mybatis-config.xml"));
            SqlSession sqlSession = sqlSessionFactory.openSession();

            try {
                consumer.accept(sqlSession);
                sqlSession.commit();
            } catch (Throwable var7) {
                if (sqlSession != null) {
                    try {
                        sqlSession.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (sqlSession != null) {
                sqlSession.close();
            }

        } catch (IOException var8) {
            throw new RuntimeException(var8);
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

package itmo.kasymov.dao.hibernate;

import java.util.List;

public abstract class HibernateDao<T> {
    public HibernateDao() {
    }

    public abstract T save(T var1);

    public abstract void deleteById(Long var1);

    public abstract void deleteByEntity(T var1);

    public abstract void deleteAll();

    public abstract T update(T var1);

    public abstract T getById(Long var1);

    public abstract List<T> getAll();
}

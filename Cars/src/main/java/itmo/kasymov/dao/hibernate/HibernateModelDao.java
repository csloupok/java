package itmo.kasymov.dao.hibernate;

import itmo.kasymov.model.Model;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateModelDao extends HibernateDao<Model> {
    public HibernateModelDao() {
    }

    public Model save(Model entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Transaction transaction = session.beginTransaction();
                session.persist(entity);
                transaction.commit();
            } catch (Throwable var7) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return entity;
    }

    public void deleteById(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Transaction transaction = session.beginTransaction();
                Model brand = session.get(Model.class, id);
                session.remove(brand);
                transaction.commit();
            } catch (Throwable var8) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public void deleteByEntity(Model entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Transaction transaction = session.beginTransaction();
                session.remove(entity);
                transaction.commit();
            } catch (Throwable var7) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public void deleteAll() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Transaction transaction = session.beginTransaction();
                Query query = session.createQuery("delete from Model");
                query.executeUpdate();
                transaction.commit();
            } catch (Throwable var6) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public Model update(Model entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Transaction transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();
            } catch (Throwable var7) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return entity;
    }

    public Model getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Model var4;
        try {
            var4 = session.get(Model.class, id);
        } catch (Throwable var7) {
            if (session != null) {
                try {
                    session.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (session != null) {
            session.close();
        }

        return var4;
    }

    public List<Model> getAll() {
        List<Model> models = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                models = session.createQuery("FROM Model", Model.class).list();
            } catch (Throwable var6) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return models;
    }

    public List<Model> getAllByBrandId(long id) {
        List<Model> models = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Query<Model> query = session.createQuery("SELECT m from Model m WHERE fk_brand.id = :id ", Model.class).setMaxResults(5);
                query.setParameter("id", id);
                models = query.list();
            } catch (Throwable var8) {
                if (session != null) {
                    try {
                        session.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (session != null) {
                session.close();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return models;
    }
}

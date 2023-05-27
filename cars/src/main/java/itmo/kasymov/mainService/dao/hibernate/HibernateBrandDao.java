package itmo.kasymov.mainService.dao.hibernate;

import itmo.kasymov.mainService.entity.Brand;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateBrandDao extends HibernateDao<Brand> {
    public HibernateBrandDao() {
    }

    public Brand save(Brand entity) {
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

    public void deleteById(Long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Transaction transaction = session.beginTransaction();
                Brand brand = session.get(Brand.class, id);
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

    public void deleteByEntity(Brand entity) {
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
                Query query = session.createQuery("delete from Brand");
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

    public Brand update(Brand entity) {
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

    public Brand getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Brand var4;
        try {
            var4 = session.get(Brand.class, id);
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

    public List<Brand> getAll() {
        List<Brand> brands = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                brands = session.createQuery("FROM Brand", Brand.class).list();
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

        return brands;
    }
}

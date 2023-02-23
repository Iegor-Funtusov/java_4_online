package ua.com.alevel;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.entity.User;

import java.util.Date;
import java.util.List;

public class HibernateCrud {

    private final HibernateConfig hibernateConfig = new HibernateConfig();

    public void crud() {
        User user = new User();
        user.setActive(true);
        user.setCreated(new Date());
        user.setEmail("user3@mail.com");
        user.setFirstName("User3");
        user.setLastName("Userenko");
        user.setAge(25);
//        user.setId(1L);

//        create1(user);
//        update1(user);
//        delete1(user);
//        delete2(user);
//        findAll();
        findById2(4L);
    }

    private void create1(User user) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // HQL
    private void update1(User user) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void delete1(User user) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // hql
    private void delete2(User user) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session
                    .createQuery("delete from User u where u.id = :idOfUser")
                    .setParameter("idOfUser", user.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void findAll() {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            List<User> users = query.getResultList();
            System.out.println("users = " + users);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void findById1(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where u.id = :id")
                    .setParameter("id", id);
            User user = (User) query.getResultList().get(0);
            System.out.println("user = " + user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void findById2(Long id) {
        Transaction transaction = null;
        try(Session session = hibernateConfig.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            System.out.println("user = " + user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

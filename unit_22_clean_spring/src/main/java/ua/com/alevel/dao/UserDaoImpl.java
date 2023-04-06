package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ua.com.alevel.entity.User;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
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

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
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

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
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

    @Override
    public Optional<User> findById(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where u.id = :id")
                    .setParameter("id", id);
            User department = (User) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(department);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User ");
            List<User> users = query.getResultList();
            transaction.commit();
            return users;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}

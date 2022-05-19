package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            Query query =   session
                            .createSQLQuery("CREATE TABLE IF NOT EXISTS User (id MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30) NOT NULL , lastName varchar(30) NOT NULL , age TINYINT NOT NULL, PRIMARY KEY (id))")
                            .addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            Query query =   session
                    .createSQLQuery("DROP TABLE User")
                    .addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        user.setId(id);
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        /*Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            CriteriaQuery criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            Root rootEntry = criteriaQuery.from(User.class);
            CriteriaQuery all = criteriaQuery.select(rootEntry);
            return session.createQuery(all).getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return new ArrayList<User>();*/

        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            List<User> listResult = query.getResultList();
            transaction.commit();
            return listResult;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return new ArrayList<User>();
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

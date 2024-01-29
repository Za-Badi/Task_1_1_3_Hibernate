package jm.task.core.jdbc.dao;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import javax.persistence.criteria.CriteriaQuery;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String s = "Alter table hibernate_id_seq ";
            String createString = "create table users_hibernate "
                    + "(id bigint AUTO_INCREMENT, "
                    + "name varchar(255) NOT NULL, "
                    + "lastName varchar(255) NOT NULL, "
                    + "age tinyint NOT NULL, "
                    + "PRIMARY KEY (id))";
            session.createSQLQuery(createString).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing to create a user table\n" + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE users_hibernate";
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing drop table\n" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail("An exception occurred while testing user save\n" + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            var entity = session.getSession().get(User.class, id);
            session.getSession().delete(entity);
            // This makes the pending delete to be done
            session.flush();
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing deleting a user by id\n " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery(User.class);
            return session.createQuery(criteria).getResultList();
//            return session.createQuery("from User").getResultList();
//            return session.createCriteria(User.class).list();
        }
        catch (Exception e){
            Assert.fail("An exception occurred while trying to get all users from the database\n" + e);
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing clearing the users table\n" + e);
        }
    }
}

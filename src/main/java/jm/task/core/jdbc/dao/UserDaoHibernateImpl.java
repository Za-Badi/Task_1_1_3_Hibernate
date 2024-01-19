package jm.task.core.jdbc.dao;
import java.util.List;
import javax.persistence.Query;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            System.out.print(e.toString());
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = String.format("DROP TABLE %s", "users_hibernate");
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createCriteria(User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.createQuery("delete User where id = id").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

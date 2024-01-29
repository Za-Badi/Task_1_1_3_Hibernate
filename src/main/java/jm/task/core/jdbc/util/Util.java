package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // set up a database connection
    private static String url = "jdbc:mysql://localhost:3306/task_1_1_3";
    private static String user = "root";
    private static String password = "zaha";
    private static SessionFactory sessionFactory;

    private Util(){
    };

    public static Connection sqlConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void setDatabaseURL(String newUrl) {
        url = newUrl;
    }

    public static void setUserName(String userName) {
        user = userName;
    }

    public static void setPassowrd(String newPassword) {
        password = newPassword;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, url + "?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "zaha");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.C3P0_MIN_SIZE, "10");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}

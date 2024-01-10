package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // set up a database connection

    private static String url  = "jdbc:mysql://localhost:3306/task_1_1_2";
    private static String user = "root";
    private static String password = "zaha";
    public static Connection sqlConnection() throws SQLException {
        return DriverManager.getConnection(url, user,password);
    }
    public static void setDatabaseURL(String newUrl){
        url = newUrl;
    }
    public static void setUserName(String userName){
        user = userName;
    }
    public static void setPassowrd(String newPassword){
        password = newPassword;
    }
}

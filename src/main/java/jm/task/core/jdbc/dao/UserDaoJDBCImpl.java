package jm.task.core.jdbc.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        String createString = "create table USERS "
                + "(id integer AUTO_INCREMENT, "
                + "name varchar(40) NOT NULL, "
                + "latsName varchar(40) NOT NULL, "
                + "age tinyint NOT NULL, "
                + "PRIMARY KEY (id))";
        try (Connection myConnection = Util.sqlConnection()) {
            try (Statement myStatement = myConnection.createStatement()) {
                myStatement.executeUpdate(createString);

            }

            catch (SQLException e) {
                System.out.println("zaha is " + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("zaha is " + e.toString());
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE USERS";
        try (Connection myConnection = Util.sqlConnection()) {
            try (Statement myStatement = myConnection.createStatement()) {
                boolean exists = myConnection.getMetaData()
                        .getTables(null, null, "USERS", null)
                        .next();
                if (exists) {
                    myStatement.executeUpdate(dropTable);
                }
            } catch (SQLException e) {
            }
        } catch (SQLException e) {
            System.out.println("zaha is " + e.toString());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection myConnection = Util.sqlConnection()) {
            String sql =
                    "INSERT INTO USERS (`name`, `latsName`, `age`) VALUES (?,?,?)";
            PreparedStatement stmt = myConnection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            int i = stmt.executeUpdate();
            if (i > 0) {
                System.out.println("User with name - "+name+" added to the database");
            }
//                        else {
            //                System.out.println("ROW NOT INSERTED");
            //            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void removeUserById(long id) {
        String dropUser = "DELETE FROM USERS "
                + "WHERE id = ?";
        try (Connection myConnection = Util.sqlConnection()) {
            try (Statement myStatement = myConnection.createStatement()) {
                PreparedStatement stmt =
                        myConnection.prepareStatement(dropUser);
                stmt.setLong(1, id);
                stmt.execute();

            } catch (SQLException e) {
            }
        } catch (SQLException e) {
            System.out.println("zaha is " + e.toString());
        }
    }

    public List<User> getAllUsers() {
        try (Connection con = Util.sqlConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from USERS");
            List<User> users = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user =
                        new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                users.add(user);
                System.out.println(user.toString());
            }
            return users;

        } catch (Exception e) {
            System.out.println("Error in getData" + e);
        }
        return null;
    }

    public void cleanUsersTable() {
        String cleanTable = "DELETE FROM USERS ";
        try (Connection myConnection = Util.sqlConnection()) {
            try (Statement myStatement = myConnection.createStatement()) {
                Statement stmt = myConnection.createStatement();
                stmt.execute(cleanTable);
            }
        } catch (SQLException e) {
        }
    }
}
package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // implement algorithm here
        UserDaoHibernateImpl user = new UserDaoHibernateImpl();
        user.dropUsersTable();
        user.createUsersTable();
//         List<User> users = user.getAllUsers();


//            System.out.println(users.get(0).getLastName());

        user.saveUser("zaha","badi",(byte) 1);
//        user.removeUserById(7);
//        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
//        user.createUsersTable();
//        user.saveUser("zaha","badi", (byte) 23);
//        user.saveUser("zaha","badi", (byte) 23);
//        user.saveUser("zaha","badi", (byte) 23);
//        user.saveUser("zaha","badi", (byte) 23);
//        user.getAllUsers();

//        user.cleanUsersTable();
//        user.dropUsersTable();



    }
}
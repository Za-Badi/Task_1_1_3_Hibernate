package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // implement algorithm here

        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("zaha","badi", (byte) 23);
        user.saveUser("zaha","badi", (byte) 23);
        user.saveUser("zaha","badi", (byte) 23);
        user.saveUser("zaha","badi", (byte) 23);
        user.getAllUsers();

        user.cleanUsersTable();
        user.dropUsersTable();



    }
}
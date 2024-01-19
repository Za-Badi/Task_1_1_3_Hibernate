package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
//    UserDaoJDBCImpl imp = new UserDaoJDBCImpl();
//UserDaoHibernateImpl imp = new UserDaoHibernateImpl();
    UserDaoJDBCImpl imp = new UserDaoJDBCImpl();
    public void createUsersTable() {
      try{
          imp.createUsersTable();}
      catch (Exception w){

      }
    }

    public void dropUsersTable() {
           imp.dropUsersTable();


    }

    public void saveUser(String name, String lastName, byte age)  {
        imp.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        imp.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return imp.getAllUsers() ;
    }

    public void cleanUsersTable() {
        imp.cleanUsersTable();
    }
}

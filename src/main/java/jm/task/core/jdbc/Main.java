package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userDaoJDBC = new UserServiceImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Elman", "Selimov", (byte) 20);
        System.out.println("User с именем – Elman добавлен в базу данных");
        userDaoJDBC.saveUser("Telman", "Selimov", (byte) 24);
        System.out.println("User с именем – Telman добавлен в базу данных");
        userDaoJDBC.saveUser("ViktOr", "Bukovskiy", (byte) 21);
        System.out.println("User с именем – ViktOr добавлен в базу данных");
        userDaoJDBC.saveUser("Mihuil", "Lutsenko", (byte) 22);
        System.out.println("User с именем – Mihuil добавлен в базу данных");
        List<User> userList = userDaoJDBC.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }

}

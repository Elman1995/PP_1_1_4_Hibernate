package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try {
            Util util = new Util();
            if (util.getConnection() != null) {
                Statement statement = util.getConnection().createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS User (id MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30) NOT NULL , lastName varchar(30) NOT NULL , age TINYINT NOT NULL, PRIMARY KEY (id))");
            }
        } catch (SQLException | ClassNotFoundException  e) {}

    }

    public void dropUsersTable() {
        try {
            Util util = new Util();
            if (util.getConnection() != null) {
                Statement statement = util.getConnection().createStatement();
                statement.executeUpdate("DROP TABLE user");
            }
        } catch (SQLException | ClassNotFoundException  e) {}

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util util = new Util();
            if (util.getConnection() != null) {
                PreparedStatement preparedStatement = util.getConnection().prepareStatement("INSERT INTO user (name, lastName, age) Values (?, ?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, (byte) age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException  e) {}
    }

    public void removeUserById(long id) {
        try {
            Util util = new Util();
            if (util.getConnection() != null) {
                PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM user WHERE id = ?");
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException  e) {}
    }

    public List<User> getAllUsers() {
        List<User> people = new ArrayList<User>();
        try {
            Util util = new Util();
            if (util.getConnection() != null) {
                PreparedStatement preparedStatement = util.getConnection().prepareStatement("SELECT * FROM user");
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    people.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
                }
            }
        } catch (SQLException | ClassNotFoundException  e) {}
        return people;
    }

    public void cleanUsersTable() {
        try {
            Util util = new Util();
            if (util.getConnection() != null) {
                PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM user");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException  e) {}
    }
}

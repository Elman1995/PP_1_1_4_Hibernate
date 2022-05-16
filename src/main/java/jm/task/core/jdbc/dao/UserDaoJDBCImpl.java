package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS User (id MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30) NOT NULL , lastName varchar(30) NOT NULL , age TINYINT NOT NULL, PRIMARY KEY (id))");
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException  e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE user");
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException  e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) Values (?, ?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, (byte) age);
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException  e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException  e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }

    public List<User> getAllUsers() {
        List<User> people = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    people.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
                }
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException  e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
        return people;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user");
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException | ClassNotFoundException  e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }

}

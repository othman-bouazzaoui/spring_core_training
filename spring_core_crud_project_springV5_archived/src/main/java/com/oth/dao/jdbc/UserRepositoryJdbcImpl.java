package com.oth.dao.jdbc;

import com.oth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryJdbcImpl implements UserRepositoryJdbc {

    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String AGE = "AGE";
    private static final String ID = "ID";
    private static final String CREATE_STATEMENT = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, AGE) values (?, ?, ?)";
    private static final String UPDATE_STATEMENT = "UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, AGE=? WHERE ID=?";
    private static final String SELECT_ALL_STATEMENT = "SELECT ID, FIRST_NAME, LAST_NAME, AGE FROM USERS";
    private static final String FIND_BY_ID_STATEMENT = "SELECT ID, FIRST_NAME, LAST_NAME, AGE FROM USERS WHERE ID = ?";
    private static final String DELETE_BY_ID_STATEMENT = "DELETE FROM USERS WHERE ID = ?";

    @Autowired
    private ConnectionManager connectionManager;


    @Override
    public User findUserById(Long id) {
        Connection connection = null;
        try {
            connection = connectionManager.openConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_STATEMENT)) {
                preparedStatement.setLong(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    return new User(rs.getLong(ID), rs.getString(FIRST_NAME), rs.getString(LAST_NAME), rs.getLong(AGE));
                }
            }
        } catch (Exception ex) {
            System.err.println("Ex = " + ex);
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            connection = connectionManager.openConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATEMENT)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getLong(ID), rs.getString(FIRST_NAME), rs.getString(LAST_NAME), rs.getLong(AGE)));
                }
            }
        } catch (Exception ex) {
            System.err.println("ex " + ex);
        } finally {
            connectionManager.closeConnection(connection);
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        Connection connection = null;
        try {
            connection = connectionManager.openConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STATEMENT, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setLong(3, user.getAge());

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    return savedUser(preparedStatement, connection);
                }
            }

        } catch (Exception ex) {
            System.err.println("ex " + ex);
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }

    @Override
    public User modifyUser(User user) {
        Connection connection = null;
        try {
            connection = connectionManager.openConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATEMENT)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setLong(3, user.getAge());
                preparedStatement.setLong(4, user.getId());

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    return user;
                }
            }

        } catch (Exception ex) {
            System.err.println("ex " + ex);
        } finally {
            connectionManager.closeConnection(connection);
        }
        return null;
    }

    @Override
    public int deleteUserById(Long id) {
        Connection connection = null;
        try {
            connection = connectionManager.openConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_STATEMENT)) {
                preparedStatement.setLong(1, id);
                return preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.err.println("Ex = " + ex);
        } finally {
            connectionManager.closeConnection(connection);
        }
        return 0;
    }

    private User savedUser(PreparedStatement preparedStatement, Connection connection) throws SQLException {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            long userId = resultSet.getLong(1);
            try (PreparedStatement preparedStatement2 = connection.prepareStatement(FIND_BY_ID_STATEMENT)) {
                preparedStatement2.setLong(1, userId);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    return new User(rs.getLong(ID), rs.getString(FIRST_NAME), rs.getString(LAST_NAME), rs.getLong(AGE));
                }
            }
        }
        return null;
    }

}

package com.wedeal.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wedeal.model.User;
import com.wedeal.util.DbUtil;

public class UserDaoExample {

    private Connection connection;

    public UserDaoExample() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(firstname,dob,email,filename,filepath) values (?, ?, ?, ?, ? )");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setDate(2, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFileName());
            preparedStatement.setString(5, user.getSavePath());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where userid=?");
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set firstname=?, dob=?, email=?, filename=?, filepath=?" +
                            "where userid=?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setDate(2, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFileName());
            preparedStatement.setString(5, user.getSavePath());
            preparedStatement.setInt(6, user.getUserid());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                user.setFileName(rs.getString("filename"));
                user.setSavePath(rs.getString("filepath"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                user.setFileName(rs.getString("filename"));
                user.setSavePath(rs.getString("filepath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
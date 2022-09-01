package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;

public class UserServiceImpl implements UserService {
    public void createUsersTable()  {
        try {
            Util.getStatement().execute("CREATE TABLE Users (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT , name  CHAR(50), lastName CHAR(50), age TINYINT)");
        }
        catch (SQLException e) {

        }

    }

    public void dropUsersTable() {
        try {
            Util.getStatement().execute("DROP TABLE Users");
        }
        catch (SQLException e) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util.getStatement().execute(String.format("INSERT INTO Users (name,lastName,age) VALUES (\"%s\",\"%s\",%d);",name,lastName,age));
        }
        catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        try {
            Util.getStatement().execute(String.format("DELETE FROM Users WHERE id=%d",id));
        }
        catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        ResultSet resultSet ;
        List<User> userList = new ArrayList<>();
        Long id;
        String name;
        String lastname;
        Byte age;
        try {
            resultSet = Util.getStatement().executeQuery("SELECT * FROM database1.users");
            while (!resultSet.isAfterLast()) {
                resultSet.next();
                id = resultSet.getLong("id");
                name = resultSet.getString("name");
                lastname = resultSet.getString("lastName");
                age = resultSet.getByte("age");
                userList.add(new User(name,lastname,age));
                userList.get(userList.size()-1).setId(id);
            }
        }
        catch (SQLException e) {

        }
            return userList;
    }

    public void cleanUsersTable() {
        try {
            Util.getStatement().execute(String.format("DROP TABLE database1.Users"));
        }
        catch (SQLException e) {

        }
    }
}

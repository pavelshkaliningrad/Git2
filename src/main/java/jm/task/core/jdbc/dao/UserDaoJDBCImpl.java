package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection conn = Util.getConnection();
        try (conn ; Statement stat = conn.createStatement()) {
            stat.execute("CREATE TABLE Users (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT , name  CHAR(50), lastName CHAR(50), age TINYINT)");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы");
        }
    }

    public void dropUsersTable() {
        Connection conn = Util.getConnection();
        try (conn ; Statement stat = conn.createStatement()) {
            stat.execute("DROP TABLE Users");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection conn = Util.getConnection();
        try (conn; Statement stat = conn.createStatement())  {
            stat.execute(String.format("INSERT INTO Users (name,lastName,age) VALUES (\"%s\",\"%s\",%d);",name,lastName,age));
        }
        catch (SQLException e) {
            System.out.println("Ошибка при создании нового пользователя");
        }
    }

    public void removeUserById(long id) {
        Connection conn = Util.getConnection();
        try (conn; Statement stat = conn.createStatement()) {
            stat.execute(String.format("DELETE FROM Users WHERE id=%d",id));
        }
        catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() {
        Connection conn = Util.getConnection();
        ResultSet resultSet ;
        List<User> userList = new ArrayList<>();
        Long id;
        String name;
        String lastname;
        Byte age;
        try (conn; Statement stat = conn.createStatement()) {
            resultSet = stat.executeQuery("SELECT * FROM database1.users");
            while (!resultSet.isLast()) {
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
            System.out.println("Ошибка при возвражении всех пользователей");
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection conn = Util.getConnection();
        try (conn; Statement stat = conn.createStatement()) {
            stat.execute(String.format("TRUNCATE TABLE database1.users"));
        }
        catch (SQLException e) {
            System.out.println("Ошибка при очистки таблицы пользователей");
        }
    }
}

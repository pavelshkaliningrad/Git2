package jm.task.core.jdbc;

import com.mysql.cj.protocol.Resultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class Main {
    public static void main(String[] args)  {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Egor","Novikov",(byte) 32);
        System.out.println("User с именем – Egor добавлен в базу данных");
        userService.saveUser("Roman","Kozlov",(byte) 10);
        System.out.println("User с именем – Roman добавлен в базу данных");
        userService.saveUser("Danil","Sokolov",(byte) 45);
        System.out.println("User с именем – Danil добавлен в базу данных");
        userService.saveUser("Sergei","Stepanov",(byte) 27);
        System.out.println("User с именем – Sergei добавлен в базу данных");
        for (User user:
                userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

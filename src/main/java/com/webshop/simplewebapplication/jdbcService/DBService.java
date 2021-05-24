package com.webshop.simplewebapplication.jdbcService;

import com.webshop.simplewebapplication.jdbcService.dao.TasksDAO;
import com.webshop.simplewebapplication.jdbcService.dao.UsersDAO;
import com.webshop.simplewebapplication.model.Task;
import com.webshop.simplewebapplication.model.Usr;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
        System.out.println("Соединение с СУБД выполнено.");
    }

    public Usr getUser(String login) throws DBException {
        try {
            return (new UsersDAO(connection).getUser(login));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Task> findAllFinished() throws DBException {
        try {
            return (new TasksDAO(connection).findAllFinished());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean checkUserExists(String login) throws DBException {
        try {
            return (new UsersDAO(connection).checkUserExists(login));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(Usr usr) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(usr.getLogin(), usr.getPassword(), "USER");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3308/").                //port
                    append("taskqueue?").          //db name
                    append("user=root&").          //login
                    append("password=root&").   //password
                    append("serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

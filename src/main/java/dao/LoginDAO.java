package dao;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import models.Login;

public class LoginDAO {
    public LoginDAO() {
    }

    public static void create(Login user) throws SQLException {

            Connection connection = DbConnection.getConnectionSqlite();
            String save = "insert into Logins (Login, Password) values (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(save);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.execute();
            connection.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registro salvo");
            alert.setContentText("Registro Salvo com Sucesso!");
            alert.show();

    }





    public static Login retrive(int id) throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        String search = "select *from users where id = ?";
        PreparedStatement stmt = connection.prepareStatement(search);
        stmt.setInt(1, id);
        ResultSet rst = stmt.executeQuery();
        Login user = new Login();

        while(rst.next()) {
            user.setId(rst.getInt("id"));
            user.setPassword(rst.getString("password"));
            user.setLogin(rst.getString("login"));
        }

        rst.close();
        connection.close();
        return user;
    }

    public static void update(Login user) throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        if (connection != null) {
            String update = "update users set name = ?, age = ?, login = ?, password = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(update);
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getId());
            stmt.close();
        }

    }

    public static List<Login> listAll() throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        Statement stmt = connection.createStatement();
        ResultSet rst = stmt.executeQuery("select *from users");
        ArrayList listUsers = new ArrayList();

        while(rst.next()) {
            Login u = new Login();
            u.setId(rst.getInt("id"));
            u.setLogin(rst.getString("login"));
            u.setPassword(rst.getString("password"));
            listUsers.add(u);
        }

        rst.close();
        connection.close();
        return listUsers;
    }
}

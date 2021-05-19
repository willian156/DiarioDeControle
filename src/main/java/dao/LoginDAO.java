package dao;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}

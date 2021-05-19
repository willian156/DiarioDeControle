package dao;

import database.DbConnection;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import models.Login;

import static controller.LoginController.usuarioLogado;

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
            stmt.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registro salvo");
            alert.setContentText("Registro Salvo com Sucesso!");
            alert.show();

    }

    public static int logar(Login login) throws SQLException{

        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:ProjetoDB.db");
        Statement stmt;
        ResultSet rst;
        stmt = connection.createStatement();
        rst = stmt.executeQuery("select *from Logins");
        ArrayList<Login> listUsers = new ArrayList<Login>();

        //Pegar os itens do resultset e inserir na lista
        while(rst.next()){
            Login u = new Login();
            u.setId(rst.getInt("id"));
            u.setLogin(rst.getString("login"));
            u.setPassword(rst.getString("password"));
            listUsers.add(u);
        }
        rst.close();
        connection.close();

        //validar login
        int log = 0;
        for (Login u : listUsers){
            if(u.getLogin().equals(login.getLogin()) && u.getPassword().equals(login.getPassword())){
                log = 1;
                usuarioLogado = u;
                break;
            }else{
                log = 0;
            }

        }
        return log;
    }
}

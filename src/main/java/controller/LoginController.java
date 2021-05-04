package controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Login;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LoginController {
    @FXML
    Button btnLogar;
    Button btnCadastro;
    @FXML
    TextField
            txt_login,
            txt_senha;

    public void login(ActionEvent event) throws SQLException, IOException {
        System.out.println("TESTE BOTÃO!");
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
        //login
        String login = txt_login.getText();
        String password = txt_senha.getText();
        //validar login
        for (Login u : listUsers){
            if(u.getLogin().equals(login) && u.getPassword().equals(password)){
                System.out.println("Login ok");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/diarios_menu.fxml"));
                Stage stage = (Stage) btnLogar.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);

                break;
            }

        }
    }

    public void checkConnection(ActionEvent event) throws SQLException {
        //Testar a conexão ao banco
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:ProjetoDB.db");
        if(connection != null){
            System.out.println("Conexão ok");
        }
        else{
            System.out.println("Falha na conexão");
        }
    }

    public void cadastrar(ActionEvent event) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:ProjetoDB.db");
        PreparedStatement stmt;
        if(connection != null){
            //Cadastrar
            String save = "insert into Logins (Login, Password) values (?, ?)";
            stmt = connection.prepareStatement(save);
            stmt.setString(1, txt_login.getText().toString());
            /*stmt.setInt(2, Integer.parseInt(textAge.getText().toString()));
            stmt.setString(3, textLogin.getText().toString());*/
            stmt.setString(2, txt_senha.getText().toString());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if(stmt.execute() == false) {
                alert.setTitle("Registro salvo");
                alert.setContentText("Registro Salvo com Sucesso!");
                alert.show();
            }else {
                alert.setTitle("Falha!");
                alert.setContentText("Falha no registro!");
                alert.show();
                stmt.close();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no DB");
            alert.setContentText("Falha na Conexão com Banco de Dados!");
            alert.show();
        }
    }

    public void search(ActionEvent event) throws SQLException {
       /* Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:unifaesp.db");
        PreparedStatement stmt;
        ResultSet rst;
        String search = "select *from users where id = ?";
        stmt = connection.prepareStatement(search);
        stmt.setInt(1, "id");
        rst = stmt.executeQuery();
        while(rst.next()){
            txt_login.setText(rst.getString("login"));
            txt_senha.setText(rst.getString("password"));
        }
        rst.close();
        stmt.close();*/

    }

    /*public void update(ActionEvent event) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:unifaesp.db");
        PreparedStatement stmt;
        if(connection != null){
            String update = "update users set name = ?, age = ?, login = ?, password = ? where id = ?";
            stmt = connection.prepareStatement(update);
            stmt.setString(1, textName.getText().toString());
            stmt.setInt(2, Integer.parseInt(textAge.getText().toString()));
            stmt.setString(3, textLogin.getText().toString());
            stmt.setString(4, textPassword.getText().toString());
            stmt.setInt(5, Integer.parseInt(textSearch.getText().toString()));
            if(stmt.execute() == false)
                System.out.println("Registro Salvo com Sucesso");
            else
                System.out.println("Falha no cadastro");
            stmt.close();

        }

    }*/
}

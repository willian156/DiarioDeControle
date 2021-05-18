package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Login;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LoginController {
    @FXML
    Button btnLogar;
    @FXML
    Button btnCadastro;
    @FXML
    TextField
            txt_login,
            txt_senha;
    public static Login usuarioLogado;


    //chamar a tela com a anterior aberta:
    /*Stage stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Cadastro.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("My modal window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
            stage.show();*/

    public void login(ActionEvent event) throws SQLException, IOException {

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        for (Login u : listUsers){
            if(u.getLogin().equals(login) && u.getPassword().equals(password)){

                usuarioLogado = u;
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/diarios_menu.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Diário Menu");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
                stage.show();

               //comentado até segundas ordens
                /*FXMLLoader loader = new FXMLLoader.load(getClass().getResource("/fxml/diarios_menu.fxml"));
                Stage stage = (Stage) btnLogar.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);*/

                break;
            }

        }
    }

    //retornar o id do usuário atual
    public void IdLogin(Login idlogin){
        idlogin = new Login();
        final int usuario = idlogin.getId();
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

}

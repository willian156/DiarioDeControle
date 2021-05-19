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

import static dao.LoginDAO.logar;
import static dao.LoginDAO.create;

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

    public void login(ActionEvent event) throws SQLException, IOException {

        Login login = new Login();

        login.setLogin(txt_login.getText());
        login.setPassword(txt_senha.getText());

        int log = logar(login);

        if(log == 1){

            scenes.DiariosScene.menuSc(event);

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuário não encontrado!");
            alert.setContentText("Usuário não encontrado! Por favor, verifique seu login e senha.");
            alert.show();
        }
    }

    public void cadastrar(ActionEvent event) throws SQLException {
        Login login = new Login();
        login.setLogin(txt_login.getText());
        login.setPassword(txt_senha.getText());
        create(login);

    }
}

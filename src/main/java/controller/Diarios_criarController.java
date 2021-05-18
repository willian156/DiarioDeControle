package controller;

import database.DbConnection;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static controller.LoginController.usuarioLogado;
import static database.DbConnection.getConnectionSqlite;

public class Diarios_criarController {
    @FXML
    TextArea txtDesc;
    @FXML
    Button btnSalvar;
    @FXML
    TextField txtData;

    public void Salvar() throws SQLException {

            Connection connection = getConnectionSqlite();
            PreparedStatement stmt;
            String save = "insert into Diarios (Criador, Data, Descricao, id_login) values (?, ?, ?, ?)";
            stmt = connection.prepareStatement(save);
            stmt.setString(1, usuarioLogado.getLogin());
            stmt.setString(2, txtData.getText());
            stmt.setString(3, txtDesc.getText());
            stmt.setInt(4, usuarioLogado.getId());
            stmt.execute();
            connection.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Diário salvo");
            alert.setContentText("Diário Salvo com Sucesso!");
            alert.show();
    }
    


}

package controller;

import database.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.Diarios_menuController.diarioSelecionado;

public class Diarios_deletarController implements Initializable {

    @FXML
    TextField txtID;
    @FXML
    Button btnSim,
           btnNao;

    public void Sim() throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        String delete = "delete from Diarios where id = ?";
        PreparedStatement stmt = connection.prepareStatement(delete);
        stmt.setInt(1, diarioSelecionado.getId());
        stmt.execute();
        stmt.close();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diário deletado");
        alert.setContentText("Diário deletado com Sucesso!");
        alert.show();

        Stage stage = (Stage)btnSim.getScene().getWindow();
        stage.close();

    }
    public void Nao(){
        Stage stage = (Stage)btnNao.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtID.setText(String.valueOf(diarioSelecionado.getId()));
    }
}

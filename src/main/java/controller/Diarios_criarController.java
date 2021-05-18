package controller;

import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static controller.LoginController.usuarioLogado;
import static database.DbConnection.getConnectionSqlite;

public class Diarios_criarController{
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

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Diário salvo");
            alert.setContentText("Diário Salvo com Sucesso!");
            alert.show();


    }
}

package controller;

import database.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.Diarios_menuController.perfilLogado;
import static controller.LoginController.usuarioLogado;

public class PerfisController implements Initializable {
    @FXML
    TextField txtNome,
              txtIdade;
    @FXML
    TextArea txtDesc;
    @FXML
    Button btnEditarPerfil,
            btnSalvar;

    public void EditarPerfil(){
            btnSalvar.setVisible(true);

    }

    public void Salvar() throws SQLException {
        Connection connection = DbConnection.getConnectionSqlite();
        perfilLogado.setNome_completo(txtNome.getText());
        perfilLogado.setIdade(Integer.parseInt(txtIdade.getText()));
        perfilLogado.setDescricao(txtDesc.getText());

        String update = "update Perfis set nome_completo = ?, idade = ?, descricao = ?, id_login = ? where id = ?";
        PreparedStatement stmt = connection.prepareStatement(update);
        stmt.setString(1, perfilLogado.getNome_completo());
        stmt.setInt(2, perfilLogado.getIdade());
        stmt.setString(3, perfilLogado.getDescricao());
        stmt.setInt(4, usuarioLogado.getId());
        stmt.setInt(5, perfilLogado.getId());
        stmt.execute();
        stmt.close();

        perfilLogado.setNome_completo(txtNome.getText());
        perfilLogado.setIdade(Integer.parseInt(txtIdade.getText()));


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Perfil atualizado!");
        alert.setContentText("Seu perfil foi atualizado com sucesso!");
        alert.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            txtNome.setText(perfilLogado.getNome_completo());
            txtIdade.setText(String.valueOf(perfilLogado.getIdade()));
            txtDesc.setText(perfilLogado.getDescricao());

        btnSalvar.setVisible(false);
    }
}

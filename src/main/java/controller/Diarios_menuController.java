package controller;

import dao.DiarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Diario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static controller.LoginController.usuarioLogado;

public class Diarios_menuController implements Initializable {
    @FXML
    Button btnPerfil;
    @FXML
    Button btnCriarDiario;
    @FXML
    Button btnLerDiario;
    @FXML
    Button btnDeletarDiario;
    @FXML
    TableView<Diario> tbDiarios;
    @FXML
    TableColumn<Diario, Integer> CLid;
    @FXML
    TableColumn<Diario, String> CLcriador;
    @FXML
    TableColumn<Diario, String> CLdescricao;
    @FXML
    TableColumn<Diario, String> CLdata;

    public static Diario diarioSelecionado;




    public void  CriarDiario(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/diarios_criar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Criar diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
    public void VerPerfil(){

    }
    public void LerDiario(ActionEvent event) throws IOException {
        diarioSelecionado = tbDiarios.getSelectionModel().getSelectedItem();
        System.out.println("diario ID: "+ diarioSelecionado.getId());

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/diarios_ler.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Ler diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
    public void DeletarDiario(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Usuário logado: ");
        System.out.println(usuarioLogado.getLogin());
        System.out.println(usuarioLogado.getId());
        this.CLid.setCellValueFactory(new PropertyValueFactory("id"));
        this.CLcriador.setCellValueFactory(new PropertyValueFactory("criador"));
        this.CLdata.setCellValueFactory(new PropertyValueFactory("data"));
        this.CLdescricao.setCellValueFactory(new PropertyValueFactory("descricao"));

        try {
            this.tbDiarios.setItems(this.DiarioList());
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
    }

    private ObservableList<Diario> DiarioList() throws SQLException {
        return FXCollections.observableArrayList(DiarioDAO.retrive(usuarioLogado.getId()));
    }

}

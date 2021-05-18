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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Diario;
import models.Perfis;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.io.IOException;

import static controller.LoginController.usuarioLogado;
import static dao.DiarioDAO.criarPerfil;
import static dao.DiarioDAO.puxarPerfil;


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
    public static Perfis perfilLogado;




    public void  CriarDiario(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/diarios_criar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Criar diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();

    }
    public void VerPerfil(ActionEvent event)throws IOException{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Perfil.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Ler diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();

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
    public void DeletarDiario(ActionEvent event)throws IOException{
        diarioSelecionado = tbDiarios.getSelectionModel().getSelectedItem();
        System.out.println("diario ID: "+ diarioSelecionado.getId());

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/diarios_deletar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Deletar diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Usuário logado: ");
        System.out.println(usuarioLogado.getLogin());
        System.out.println(usuarioLogado.getId());

        try {
            perfilLogado = puxarPerfil(usuarioLogado.getId());
        } catch (SQLException throwables) {
            try {
                perfilLogado = criarPerfil(usuarioLogado);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Perfil criado!");
                alert.setContentText("Foi criado um perfil para você! Vá em 'ver perfil' para atualiza-lo!");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Perfil não criado!");
                alert.setContentText("Não foi possível criar o seu perfil!");
                alert.show();
            }
        }

        this.CLid.setCellValueFactory(new PropertyValueFactory("id"));
        this.CLcriador.setCellValueFactory(new PropertyValueFactory("criador"));
        this.CLdata.setCellValueFactory(new PropertyValueFactory("data"));
        this.CLdescricao.setCellValueFactory(new PropertyValueFactory("descricao"));

        try {
            this.tbDiarios.setItems(this.DiarioList());
        } catch (SQLException var4) {
            System.out.println("ERRO AO OBTER A LISTA!");
        }
    }

    private ObservableList<Diario> DiarioList() throws SQLException {
        return FXCollections.observableArrayList(DiarioDAO.retrive(usuarioLogado.getId()));
    }

}

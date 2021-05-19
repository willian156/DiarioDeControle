package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.Diarios_menuController.diarioSelecionado;

public class Diarios_lerController implements Initializable {

    @FXML
    TextArea txtDesc;
    @FXML
    TextField txtCriador;
    @FXML
    TextField txtDia;
    @FXML
    Button btnVoltar,
            btnImprimir;

    public void Voltar() throws IOException {
        Stage stage = (Stage)btnVoltar.getScene().getWindow();
        stage.close();
    }

    public void Imprimir() throws IOException {

        FileWriter dro = new FileWriter("d:\\Diarios_impressos\\DiarioID_"+ diarioSelecionado.getId()+ ".txt");
        PrintWriter gravarDro = new PrintWriter(dro);

        gravarDro.println("Diário realizado no dia "+ diarioSelecionado.getData()+"\n");
        gravarDro.println("Criado por: "+ diarioSelecionado.getCriador() + "\n");
        gravarDro.println("\nDescrição:\n"+ diarioSelecionado.getDescricao());

        dro.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Arquivo salvo!");
        alert.setContentText("Arquivo salvo do disco 'D' com o nome de 'DiarioID_"+diarioSelecionado.getId() +"'");
        alert.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String  desc,
                cria,
                dia;

        desc = diarioSelecionado.getDescricao();
        cria = diarioSelecionado.getCriador();
        dia = diarioSelecionado.getData();

        txtDesc.setText(desc);
        txtCriador.setText(cria);
        txtDia.setText(dia);
    }
}

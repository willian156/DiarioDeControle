package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

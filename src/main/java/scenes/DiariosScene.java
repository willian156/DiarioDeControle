package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

public class DiariosScene {

    public static void menuSc(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(DiariosScene.class.getResource("/fxml/diarios_menu.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();

    }

    public static void criarSc(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(DiariosScene.class.getResource("/fxml/diarios_criar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Criar diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }

    public static void lerSc(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(DiariosScene.class.getResource("/fxml/diarios_ler.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Ler diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();

    }

    public static void deletarSc(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(DiariosScene.class.getResource("/fxml/diarios_deletar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Deletar diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();

    }

    public static void perfilSc(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(DiariosScene.class.getResource("/fxml/Perfil.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Ler diário");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();

    }
}

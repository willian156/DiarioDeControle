import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private static Stage stage;
    private static Scene mainScene;
    private static Scene diarios_menuScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        //Parent fxmlDiarios_menu = FXMLLoader.load(getClass().getResource("/fxml/diarios_menu.fxml"));
        mainScene = new Scene(fxmlLogin);
        //diarios_menuScene = new Scene(fxmlDiarios_menu);
        primaryStage.setScene(mainScene);
        //primaryStage.setMaximized(true);
        //primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);
    }
}


//Método para alternar entre telas
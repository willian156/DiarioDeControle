import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private static Stage stage;
    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        mainScene = new Scene(fxmlLogin);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);
    }
}


//Método para alternar entre telas
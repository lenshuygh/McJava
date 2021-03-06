package be.mcjava;

import be.mcjava.controller.ViewManager;
import be.mcjava.dao.DaoConnector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CustomerMain extends Application {
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        DaoConnector.checkDbCredentials();
        
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        ViewManager viewManager = new ViewManager();
        ViewManager.scene = scene;
        ViewManager.stage = stage;
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerLoginScreen.fxml");
        ViewManager.setStageDimensions(650.0, 450.0);
        
        
        stage.setScene(scene);
        stage.show();
    }
}

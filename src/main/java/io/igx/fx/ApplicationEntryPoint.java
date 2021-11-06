package io.igx.fx;

import com.gluonhq.ignite.micronaut.FXApplication;
import io.igx.fx.views.main.MainView;
import io.micronaut.runtime.event.annotation.EventListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationEntryPoint {

    @Inject
    FXMLLoader loader;

    @Inject
    private MainView mainView;

    @EventListener
    void applicationStarted(FXApplication.StartEvent event) {
        Stage stage = event.getStage();
        Scene scene = new Scene(mainView.getRoot());
        stage.setScene(scene);
        stage.setTitle("Micronaut FX");
        stage.show();
    }


}

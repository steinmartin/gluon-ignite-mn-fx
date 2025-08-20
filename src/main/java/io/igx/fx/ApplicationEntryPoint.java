package io.igx.fx;

import com.gluonhq.ignite.micronaut.FXApplication;
import io.igx.fx.views.main.MainView;
import io.micronaut.runtime.event.annotation.EventListener;

import javafx.scene.Scene;
import javafx.stage.Stage;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ApplicationEntryPoint {


    private final MainView mainView;

    @Inject
    public ApplicationEntryPoint(MainView mainView)  {
        this.mainView = mainView;
    }


    @EventListener
    void applicationStarted(FXApplication.StartEvent event) {

        System.out.println("Application started from ApplicationEntryPoint");

        Stage stage = event.getStage();
        Scene scene = new Scene(mainView.getRoot());
        stage.setScene(scene);
        stage.setTitle("Micronaut FX");
        stage.show();
    }

}

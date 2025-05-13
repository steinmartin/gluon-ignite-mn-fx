package io.igx.fx.views.main;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import javafx.scene.layout.AnchorPane;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import javafx.scene.layout.StackPane;

@Singleton
public class MainView extends FXMLView<StackPane> {

    @PostConstruct
    public void init() {

        System.out.println("MainView init");
    }
}

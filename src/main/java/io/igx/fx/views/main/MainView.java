package io.igx.fx.views.main;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import javafx.scene.layout.AnchorPane;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class MainView extends FXMLView<AnchorPane> {

    @PostConstruct
    public void init() {
    }
}

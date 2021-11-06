package io.igx.fx.views.main;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import javafx.scene.layout.AnchorPane;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainView extends FXMLView<AnchorPane> {

    @Inject
    MainViewController mainViewController;

    @PostConstruct
    public void init() {

    }


}

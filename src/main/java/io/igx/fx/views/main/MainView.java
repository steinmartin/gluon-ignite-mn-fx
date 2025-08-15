package io.igx.fx.views.main;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import io.igx.fx.views.frame.FrameView;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.scene.layout.AnchorPane;

import static javafx.scene.layout.AnchorPane.setTopAnchor;

@Singleton
public class MainView extends FXMLView<AnchorPane> {


    @Inject
    FrameView frameView;

//    @Inject
//    public MainView(FrameView frameView)     {
//        this.frameView = frameView;
//    }

    @PostConstruct
    public void init() {
        System.out.println("FrameView init");
        setTopAnchor(frameView, 10.0);

    }
}

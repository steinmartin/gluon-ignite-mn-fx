package io.igx.fx.views.main;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import io.igx.fx.views.frame.FrameView;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.scene.layout.BorderPane;

@Singleton
public class MainView extends FXMLView<BorderPane> {



    private final FrameView frameView;

    @Inject
    public MainView(FrameView frameView)     {
        this.frameView = frameView;
    }

    @PostConstruct
    public void init() {
        System.out.println("FrameView init");

//        AnchorPane.setLeftAnchor(frameView, 50.0);
//        AnchorPane.setRightAnchor(frameView, 70.0);
//        getRoot().getChildren().add(frameView);
//
//
//        AnchorPane.setTopAnchor(frameView, 100.0);
//        AnchorPane.setLeftAnchor(frameView, 50.0);
//        AnchorPane.setRightAnchor(frameView, 70.0);

        getRoot().setCenter(frameView.getRoot());

    }
}

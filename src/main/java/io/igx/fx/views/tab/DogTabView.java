package io.igx.fx.views.tab;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

@Singleton
public class DogTabView extends FXMLView<Label> {

    @PostConstruct
    public void init() {
    }
}

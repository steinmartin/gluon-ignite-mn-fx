package io.igx.fx.views.tab;


import com.gluonhq.ignite.micronaut.view.FXMLView;
import io.micronaut.context.annotation.Prototype;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.SQLOutput;

@Prototype
public class DogTabView extends FXMLView<VBox> {

    @PostConstruct
    public void init() {
        System.out.println("DogTabView init");
    }
    @PreDestroy
    public void destroy() {
         System.out.println("DogTabView destroyed");
    }

}

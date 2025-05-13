package io.igx.fx.views.main;

import com.gluonhq.ignite.micronaut.FXMLRootProvider;
import com.gluonhq.ignite.micronaut.OnFXThread;
import io.igx.fx.model.DogMessage;
import io.igx.fx.views.tab.DogTabView;
import io.igx.fx.views.tab.DogTabViewController;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanContext;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

import java.io.IOException;


@Singleton
public class MainViewController {

    @FXML
    TabPane tabPaneImageFrame;

    @Inject
    private ApplicationContext ctx;

    private static int numberOfTabs = 0;


    @OnFXThread
    public void call(ActionEvent event) {

        DogTabView dogTabView = ctx.getBean(DogTabView.class);

        Tab tab = new Tab(Integer.toString(++numberOfTabs));
        tab.setContent(dogTabView.getRoot());
        tab.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                dogTabView.destroy();
            }
        });


        tabPaneImageFrame.getTabs().add(tab);
        tabPaneImageFrame.getSelectionModel().select(tab);
    }
}

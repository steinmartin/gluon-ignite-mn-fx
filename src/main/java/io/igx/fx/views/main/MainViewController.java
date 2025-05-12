package io.igx.fx.views.main;

import com.gluonhq.ignite.micronaut.OnFXThread;
import io.igx.fx.model.DogMessage;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
//@Prototype
public class MainViewController {

    @FXML
    TabPane imageFrameTabPane;

    @Inject
    private DogsHttpClient dogsHttpClient;

//    private MainViewSubscriber mainViewSubscriber = null;
    private Publisher<DogMessage> dogHttpResponse = null;

    @OnFXThread
    public void call(ActionEvent event) {

//        if (dogHttpResponse == null) {
//            System.out.println(" dogHttpResponse initialized");
//            dogHttpResponse = dogsHttpClient.getRandomDog();
//        }
//        MainViewSubscriber mainViewSubscriber = new MainViewSubscriber<DogMessage>();
//
//        System.out.println("mainViewSubscriber: " + mainViewSubscriber);
//        dogHttpResponse.subscribe(mainViewSubscriber);



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/tab/DogTabView.fxml"));
        try {
            fxmlLoader.load();
        } catch( IOException exception) {
            throw new RuntimeException( exception);
        }


        imageFrameTabPane.getTabs().add(fxmlLoader.getRoot());


    }
}
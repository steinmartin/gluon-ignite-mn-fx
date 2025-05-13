package io.igx.fx.views.tab;

import com.gluonhq.ignite.micronaut.OnFXThread;
import io.igx.fx.model.DogMessage;
import io.igx.fx.views.main.DogsHttpClient;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

import java.net.URL;
import java.util.ResourceBundle;


@Prototype
public class DogTabViewController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    StackPane stackPaneImageFrame;


    @Inject
    private DogsHttpClient dogsHttpClient;




    public void call(ActionEvent event) {

        Publisher<DogMessage> dogHttpResponse = dogsHttpClient.getRandomDog();
        MainViewSubscriber mainViewSubscriber = new MainViewSubscriber<DogMessage>();
        dogHttpResponse.subscribe(mainViewSubscriber);
    }

    @OnFXThread
    protected void updateImageView(String href) {
        if (stackPaneImageFrame != null) {
            Image image = new Image(href);
            double nativeWidth = image.getWidth();
            double nativeHeight = image.getHeight();
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.maxWidth(500);
            imageView.maxHeight(375);
            if (nativeHeight > 375) {
                imageView.setFitHeight(375);
            }
            if (nativeWidth > 500) {
                imageView.setFitWidth(500);
            }
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            stackPaneImageFrame.getChildren().clear();
            stackPaneImageFrame.getChildren().add(imageView);
        }
    }

    private class MainViewSubscriber<T> extends BaseSubscriber<T> {

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            requestUnbounded();
        }

        @Override
        protected void hookOnError(Throwable throwable) {
            System.err.println("Connection did not work well: " + throwable);
        }

        @Override
        protected void hookFinally(SignalType type) {
        }

        @Override
        protected void hookOnNext(T value) {
            String href = ((DogMessage) value).getMessage();
            System.out.println("HREF of Rest call: " + href);
            updateImageView(href);
        }
    }
}
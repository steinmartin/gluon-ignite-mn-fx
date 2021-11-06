package io.igx.fx.views.main;

import com.gluonhq.ignite.micronaut.OnFXThread;
import io.igx.fx.model.DogResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
public class MainViewController {

    @Client("https://dog.ceo/api")
    @Inject
    RxHttpClient httpClient;


    @FXML
    StackPane imageFrame;

    public void call(ActionEvent event) {
        httpClient.retrieve(HttpRequest.GET("/breeds/image/random"), DogResponse.class)
                .subscribe(dogResponse -> updateImageView(dogResponse.getMessage()));
    }


    void updateImageView(String href){
        if(this.imageFrame != null) {
            if(!Platform.isFxApplicationThread()){
                Platform.runLater(() -> {
                    Image image = new Image(href);
                    double nativeWidth = image.getWidth();
                    double nativeHeight = image.getHeight();
                    ImageView imageView = new ImageView(image);
                    imageView.setPreserveRatio(true);
                    imageView.maxWidth(500);
                    imageView.maxHeight(375);
                    if(nativeHeight > 375) {
                        imageView.setFitHeight(375);
                    }
                    if(nativeWidth > 500){
                        imageView.setFitWidth(500);
                    }
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    imageFrame.getChildren().clear();
                    imageFrame.getChildren().add(imageView);
                    StackPane.setAlignment(imageView, Pos.CENTER);
                });
            }
        }
    }
}
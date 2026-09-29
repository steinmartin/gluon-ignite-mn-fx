package io.igx.fx.views.main;

import io.igx.fx.model.DogResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.jspecify.annotations.NonNull;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;


/**
 * FXML controller for the main view.
 * <p>
 * Wires the main user interface to the reactive {@link DogsHttpClient}.
 * When the user requests a new dog via {@link #call(ActionEvent)}, this
 * controller subscribes to the {@link org.reactivestreams.Publisher}
 * produced by {@link DogsHttpClient#getRandomDog()}. As each
 * {@link DogResponse} arrives, the dog image referenced by its URL is
 * loaded and displayed in {@link #imageFrame}.
 * </p>
 * <p>
 * The HTTP client is non-blocking, so responses are delivered on a
 * background thread. All UI mutations are therefore marshalled back to
 * the JavaFX Application Thread with {@link Platform#runLater(Runnable)}.
 * </p>
 *
 * @see DogsHttpClient
 * @see DogResponse
 * @since 0.1
 */
@Singleton
public class MainViewController {

    /**
     * The container into which the fetched dog image is placed.
     * Injected from the FXML view.
     */
    @FXML
    StackPane imageFrame;

    /** The reactive HTTP client used to fetch a random dog. */
    @Inject
    private DogsHttpClient dogsHttpClient;

    /**
     * FXML event handler invoked when the user requests a new random dog.
     * <p>
     * Subscribes to the reactive stream produced by
     * {@link DogsHttpClient#getRandomDog()}. Each emitted
     * {@link DogResponse} is handled by the inner
     * {@link MainViewSubscriber}, which loads and displays the image.
     * </p>
     *
     * @param event the action event fired by the triggering control
     */
    public void call(ActionEvent event) {
        Publisher<DogResponse> dogHttpResponse = dogsHttpClient.getRandomDog();
        dogHttpResponse.subscribe(new MainViewSubscriber<>());
    }

    /**
     * Loads the image at the given URL and displays it in
     * {@link #imageFrame}.
     * <p>
     * The image is scaled to fit within a 500 &times; 375 bounding box
     * while preserving its aspect ratio and is rendered smoothed.
     * </p>
     *
     * @param href the URL of the image to load
     */
    private void updateImageView(String href) {
        if (this.imageFrame != null) {
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
            imageFrame.getChildren().clear();
            imageFrame.getChildren().add(imageView);
        }
    }

    /**
     * Internal subscriber that consumes the reactive {@link DogResponse}
     * stream and drives the UI update.
     * <p>
     * {@link #hookOnNext(Object)} is invoked on a background thread; it
     * extracts the image URL from the response and hops to the JavaFX
     * Application Thread to update the view. Connection failures are
     * reported on {@code System.err}.
     * </p>
     *
     * @param <T> the type of element emitted by the stream
     */
    private class MainViewSubscriber<T> extends BaseSubscriber<T> {

        @Override
        protected void hookOnSubscribe(@NonNull Subscription subscription) {
            requestUnbounded();
        }

        @Override
        protected void hookOnError(@NonNull Throwable throwable) {
            System.err.println("Connection did not work well: " + throwable);
        }

        @Override
        protected void hookOnNext(T value) {
            String href = ((DogResponse) value).message();
            System.out.println("HREF of Rest call: " + href);
            Platform.runLater(() -> updateImageView(href));
        }
    }
}
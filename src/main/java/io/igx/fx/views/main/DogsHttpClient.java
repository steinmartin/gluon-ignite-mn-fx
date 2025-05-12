package io.igx.fx.views.main;


import io.igx.fx.model.DogMessage;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;


@Client(id = "dogsclient")
public interface DogsHttpClient {

    @Get("/breeds/image/random")
    @SingleResult
    Publisher<DogMessage> getRandomDog();

}

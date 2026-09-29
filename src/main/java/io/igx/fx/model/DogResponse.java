package io.igx.fx.model;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record DogResponse(String message, String status) {
}

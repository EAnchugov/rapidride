package com.aston.rapidride.utility;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TextConstants {

    BRAND_NOT_FOUND("Brand not found"),
    COLOR_NOT_FOUND("Color not found"),
    MODEL_NOT_FOUND("Model not found"),
    CARD_NOT_FOUND("Card not found"),
    ENGINE_TYPE_NOT_FOUND("Engine type not found");

    private final String description;

    public String get() {
        return description;
    }
}

package com.aston.rapidride.utility;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TextConstants {

    BRAND_NOT_FOUND("Brand not found"),
    COLOR_NOT_FOUND("Color not found"),
    MODEL_NOT_FOUND("Model not found"),
    CARD_NOT_FOUND("Card not found"),
    ENGINE_TYPE_NOT_FOUND("Engine type not found"),
    ROLE_NOT_FOUND("Role not found"),
    USER_DOCUMENT_NOT_FOUND("User document not found"),
    DOCUMENT_TYPE_NOT_FOUND("Document type not found"),
    CAR_NOT_FOUND("Car not found"),
    CAR_STATUS_NOT_FOUND("Car status not found"),
    BOOKING_NOT_FOUND("Booking not found"),
    USER_NOT_FOUND("User not found"),
    PAYMENT_NOT_FOUND("Payment not found"),
    BOOKING_STATUS_NOT_FOUND("Booking status not found");


    private final String description;

    public String get() {
        return description;
    }
}

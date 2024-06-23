package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.BookingRequest;
import com.aston.rapidride.dto.response.BookingResponse;
import com.aston.rapidride.entity.Booking;
import org.springframework.stereotype.Component;


@Component
public class BookingMapper {

    public BookingResponse mapToResponse(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setCar(booking.getCar());
        response.setUser(booking.getUser());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setTotalAmount(booking.getTotalAmount());
        response.setPayment(booking.getPayment());
        response.setStatus(booking.getStatus());
        response.setComments(booking.getComments());

        return response;
    }

    public Booking mapToEntity(BookingRequest request) {
        if (request == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setComments(request.getComments());

        return booking;
    }
}

package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.entity.BookingStatus;
import org.springframework.stereotype.Component;


@Component
public class BookingStatusMapper {

    public BookingStatusResponse mapToResponse(BookingStatus bookingStatus) {
        if (bookingStatus == null) {
            return null;
        }

        BookingStatusResponse response = new BookingStatusResponse();
        response.setId(bookingStatus.getId());
        response.setName(bookingStatus.getName());

        return response;
    }

    public BookingStatus mapToEntity(BookingStatusRequest request) {
        if (request == null) {
            return null;
        }

        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setName(request.getName());

        return bookingStatus;
    }
}

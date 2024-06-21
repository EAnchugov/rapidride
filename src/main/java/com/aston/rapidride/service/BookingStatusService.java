package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;

import java.util.List;

public interface BookingStatusService {

    BookingStatusResponse getById(Long id);

    void createBookingStatus(BookingStatusRequest request);

    BookingStatusResponse updateBookingStatus(Long id, BookingStatusRequest request);

    List<BookingStatusResponse> getAllBookingStatuses();

}

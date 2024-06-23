package com.aston.rapidride.service;

import com.aston.rapidride.dto.request.BookingRequest;
import com.aston.rapidride.dto.request.DateRequest;
import com.aston.rapidride.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse getById(Long id);

    void createBooking(BookingRequest request);

    BookingResponse updateBooking(Long id, BookingRequest request);

    List<BookingResponse> getAllBookings();

    List<BookingResponse> getBookingsByStatusId(Long statusId);

    List<BookingResponse> getBookingsByUserId(Long userId);

    List<BookingResponse> getBookingsByCarId(Long carId);

    List<BookingResponse> getBookingsByUserIdAndCarId(Long userId, Long carId);

    List<BookingResponse> getBookingsByDates(DateRequest request);

    BookingResponse getBookingByPaymentId(Long paymentId);

}

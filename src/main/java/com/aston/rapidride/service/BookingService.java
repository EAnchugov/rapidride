package com.aston.rapidride.service;

import com.aston.rapidride.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking getById(Long id);

    void createBooking(Booking fine);

    void updateBooking(Booking fine);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByStatus(BookingStatus status);

    List<Booking> getBookingsByUserId(Long userId);

    List<Booking> getBookingsByCarId(Long carId);

    List<Booking> getBookingsByUserIdAndCarId(Long userId, Long carId);

    List<Booking> getBookingsByDate(LocalDate startDate, LocalDate endDate);

    List<Booking> getBookingsByTotalAmount(BigDecimal totalAmount);

    Booking getBookingByPaymentId(Long id);

}

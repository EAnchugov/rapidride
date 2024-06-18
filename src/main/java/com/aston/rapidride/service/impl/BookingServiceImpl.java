package com.aston.rapidride.service.impl;

import com.aston.rapidride.entity.*;
import com.aston.rapidride.service.BookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Override
    public Booking getById(Long id) {
        return null;
    }

    @Override
    public void createBooking(Booking fine) {

    }

    @Override
    public void updateBooking(Booking fine) {

    }

    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByCarId(Long carId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByUserIdAndCarId(Long userId, Long carId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByTotalAmount(BigDecimal totalAmount) {
        return List.of();
    }

    @Override
    public Booking getBookingByPaymentId(Long id) {
        return null;
    }

    @Override
    public User getUserByBookingUserId(Long id) {
        return null;
    }

    @Override
    public Car getCarByBookingCarId(Long id) {
        return null;
    }

    @Override
    public Payment getPaymentByBookingCarId(Long id) {
        return null;
    }
}

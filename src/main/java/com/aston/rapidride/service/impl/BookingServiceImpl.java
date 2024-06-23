package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BookingMapper;
import com.aston.rapidride.dto.request.BookingRequest;
import com.aston.rapidride.dto.request.DateRequest;
import com.aston.rapidride.dto.response.BookingResponse;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.*;
import com.aston.rapidride.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.aston.rapidride.utility.TextConstants.*;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final BookingMapper mapper;

    @Override
    public BookingResponse getById(Long id) {
        return bookingRepository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(BOOKING_NOT_FOUND.get()));
    }

    @Override
    public void createBooking(BookingRequest request) {
        Booking booking = mapper.mapToEntity(request);

        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND.get()));
        booking.setCar(car);

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.get()));
        booking.setUser(user);

        Payment payment = paymentRepository.findById(request.getPaymentId()).orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.get()));
        booking.setPayment(payment);

        BookingStatus bookingStatus = bookingStatusRepository.findById(request.getStatusId()).orElseThrow(()
                -> new NotFoundException(BOOKING_STATUS_NOT_FOUND.get()));
        booking.setStatus(bookingStatus);

        BigDecimal totalAmount = getTotalAmount(car, booking.getStartDate(), booking.getEndDate());
        booking.setTotalAmount(totalAmount);
        bookingRepository.save(booking);
    }

    @Override
    public BookingResponse updateBooking(Long id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOKING_NOT_FOUND.get()));

        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setComments(request.getComments());

        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> new NotFoundException(CAR_NOT_FOUND.get()));
        booking.setCar(car);

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.get()));
        booking.setUser(user);

        Payment payment = paymentRepository.findById(request.getPaymentId()).orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.get()));
        booking.setPayment(payment);

        BookingStatus bookingStatus = bookingStatusRepository.findById(request.getStatusId()).orElseThrow(()
                -> new NotFoundException(BOOKING_STATUS_NOT_FOUND.get()));
        booking.setStatus(bookingStatus);

        BigDecimal totalAmount = getTotalAmount(car, booking.getStartDate(), booking.getEndDate());
        booking.setTotalAmount(totalAmount);

        bookingRepository.save(booking);

        return mapper.mapToResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByStatusId(Long statusId) {
        BookingStatus bookingStatus = bookingStatusRepository.findById(statusId).orElseThrow(()
                -> new NotFoundException(BOOKING_STATUS_NOT_FOUND.get()));
        List<Booking> bookings = bookingRepository.findBookingsByStatus(bookingStatus);
        return bookings.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new NotFoundException(USER_NOT_FOUND.get()));
        List<Booking> bookings = bookingRepository.findBookingsByUser(user);
        return bookings.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByCarId(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new NotFoundException(CAR_NOT_FOUND.get()));
        List<Booking> bookings = bookingRepository.findBookingsByCar(car);
        return bookings.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByUserIdAndCarId(Long userId, Long carId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new NotFoundException(USER_NOT_FOUND.get()));
        Car car = carRepository.findById(carId).orElseThrow(()
                -> new NotFoundException(CAR_NOT_FOUND.get()));
        List<Booking> bookings = bookingRepository.findBookingsByUserAndCar(user, car);
        return bookings.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByDates(DateRequest request) {
        List<Booking> bookings = bookingRepository.findByDate(request.getStartDate(), request.getEndDate());
        return bookings.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingByPaymentId(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()
                -> new NotFoundException(PAYMENT_NOT_FOUND.get()));
        Booking booking = bookingRepository.findBookingByPayment(payment);
        return mapper.mapToResponse(booking);
    }

    private BigDecimal getTotalAmount(Car car, LocalDate startDate, LocalDate endDate) {
        int days = 0;
        while (!endDate.equals(startDate)) {
            startDate = startDate.plusDays(1);
            days++;
        }

        return BigDecimal.valueOf((long) car.getPrice() * days);
    }
}

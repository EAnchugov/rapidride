package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BookingMapper;
import com.aston.rapidride.dto.request.BookingRequest;
import com.aston.rapidride.dto.request.DateRequest;
import com.aston.rapidride.dto.response.BookingResponse;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.*;
import com.aston.rapidride.service.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BookingStatusRepository bookingStatusRepository;

    @Mock
    private BookingMapper mapper;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    @DisplayName("Test find booking by id")
    public void testGetById() {
        Long id = 1L;
        Booking booking = new Booking();
        booking.setId(id);

        BookingResponse expected = new BookingResponse();
        expected.setId(id);

        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(expected);

        BookingResponse result = bookingService.getById(id);

        assertEquals(expected.getId(), result.getId());
        Mockito.verify(bookingRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Test find booking by id, but id not found")
    public void testGetByIdNotFound() {
        Long id = 1L;
        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            bookingService.getById(id);
        });

        assertEquals("Booking not found", thrown.getMessage());
        verify(bookingRepository, Mockito.times(1)).findById(id);
        verify(mapper, never()).mapToResponse(any(Booking.class));
    }

    @Test
    @DisplayName("Test booking create")
    public void testCreateBooking() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);
        request.setPaymentId(id);
        request.setStatusId(id);
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now());
        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Payment payment = new Payment();
        Mockito.when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        BookingStatus bookingStatus = new BookingStatus();
        Mockito.when(bookingStatusRepository.findById(id)).thenReturn(Optional.of(bookingStatus));

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setUser(user);
        booking.setPayment(payment);
        booking.setStatus(bookingStatus);
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now());

        Mockito.when(mapper.mapToEntity(request)).thenReturn(booking);

        bookingService.createBooking(request);

        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingStatusRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).save(booking);
    }

    @Test()
    @DisplayName("Test booking create, but car not found")
    public void testCreateBookingCarNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);

        Booking booking = new Booking();
        when(mapper.mapToEntity(request)).thenReturn(booking);
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.createBooking(request);
        });

        assertEquals("Car not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test()
    @DisplayName("Test booking create, but user not found")
    public void testCreateBookingUserNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);

        Booking booking = new Booking();
        when(mapper.mapToEntity(request)).thenReturn(booking);

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.createBooking(request);
        });

        assertEquals("User not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test()
    @DisplayName("Test booking create, but payment not found")
    public void testCreateBookingPaymentNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);
        request.setPaymentId(id);

        Booking booking = new Booking();
        when(mapper.mapToEntity(request)).thenReturn(booking);

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.createBooking(request);
        });

        assertEquals("Payment not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test()
    @DisplayName("Test booking create, but status not found")
    public void testCreateBookingStatusNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);
        request.setPaymentId(id);
        request.setStatusId(id);

        Booking booking = new Booking();
        when(mapper.mapToEntity(request)).thenReturn(booking);

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Payment payment = new Payment();
        Mockito.when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        when(bookingStatusRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.createBooking(request);
        });

        assertEquals("Booking status not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingStatusRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    @DisplayName("Test booking update")
    public void testUpdateBooking() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);
        request.setPaymentId(id);
        request.setStatusId(id);
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now());

        BookingResponse expected = new BookingResponse();
        expected.setId(id);

        Booking booking = new Booking();
        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(expected);

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Payment payment = new Payment();
        Mockito.when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        BookingStatus bookingStatus = new BookingStatus();
        Mockito.when(bookingStatusRepository.findById(id)).thenReturn(Optional.of(bookingStatus));

        BookingResponse result = bookingService.updateBooking(id, request);

        assertEquals(expected, result);

        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingStatusRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).save(booking);
    }

    @Test()
    @DisplayName("Test booking update, but car not found")
    public void testUpdateBookingCarNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);

        Booking booking = new Booking();
        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        when(carRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.updateBooking(id, request);
        });

        assertEquals("Car not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test()
    @DisplayName("Test booking update, but user not found")
    public void testUpdateBookingUserNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);

        Booking booking = new Booking();
        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.updateBooking(id, request);
        });

        assertEquals("User not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test()
    @DisplayName("Test booking update, but payment not found")
    public void testUpdateBookingPaymentNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);
        request.setPaymentId(id);

        Booking booking = new Booking();
        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.updateBooking(id, request);
        });

        assertEquals("Payment not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test()
    @DisplayName("Test booking update, but status not found")
    public void testUpdateBookingStatusNotFound() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        request.setCarId(id);
        request.setUserId(id);
        request.setPaymentId(id);
        request.setStatusId(id);

        Booking booking = new Booking();
        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        Car car = new Car();
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Payment payment = new Payment();
        Mockito.when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        when(bookingStatusRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.updateBooking(id, request);
        });

        assertEquals("Booking status not found", thrown.getMessage());
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingStatusRepository, Mockito.times(1)).findById(id);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    @DisplayName("Test find all bookings")
    public void testGetAllBookings() {
        Long id = 1L;
        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(id);
        bookings.add(booking);

        List<BookingResponse> bookingResponses = new ArrayList<>();
        BookingResponse response = new BookingResponse();
        response.setId(id);
        bookingResponses.add(response);

        Mockito.when(bookingRepository.findAll()).thenReturn(bookings);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(response);

        List<BookingResponse> result = bookingService.getAllBookings();

        assertEquals(bookingResponses.size(), result.size());

        Mockito.verify(bookingRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test find all bookings, but result list is empty")
    public void testFindAllEmptyList() {
        when(bookingRepository.findAll()).thenReturn(Collections.emptyList());

        List<BookingResponse> result = bookingService.getAllBookings();

        assertTrue(result.isEmpty());

        verify(bookingRepository, times(1)).findAll();
        verify(mapper, never()).mapToResponse(any(Booking.class));
    }

    @Test
    @DisplayName("Test find all bookings by status id")
    public void testGetBookingsByStatusId() {
        Long id = 1L;
        BookingStatus status = new BookingStatus();
        status.setId(id);

        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(id);
        booking.setStatus(status);
        bookings.add(booking);

        List<BookingResponse> bookingResponses = new ArrayList<>();
        BookingResponse response = new BookingResponse();
        response.setId(id);
        response.setStatus(status);
        bookingResponses.add(response);

        Mockito.when(bookingStatusRepository.findById(id)).thenReturn(Optional.of(status));
        Mockito.when(bookingRepository.findBookingsByStatus(status)).thenReturn(bookings);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(response);

        List<BookingResponse> result = bookingService.getBookingsByStatusId(status.getId());

        assertEquals(bookingResponses.size(), result.size());
        assertEquals(bookingResponses.get(0).getStatus(), result.get(0).getStatus());

        Mockito.verify(bookingStatusRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).findBookingsByStatus(status);
    }

    @Test
    @DisplayName("Test find all bookings by status id, but status id not found")
    public void testGetBookingsByStatusIdButStatusIdNotFound() {
        Long id = 1L;
        Mockito.when(bookingStatusRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.getBookingsByStatusId(id);
        });

        assertEquals("Booking status not found", thrown.getMessage());

        verify(bookingStatusRepository, times(1)).findById(id);
        verify(bookingRepository, never()).findBookingsByStatus(any(BookingStatus.class));
    }

    @Test
    @DisplayName("Test find all bookings by user id")
    public void testGetBookingsByUserId() {
        Long id = 1L;

        User user = new User();
        user.setId(id);

        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(id);
        booking.setUser(user);
        bookings.add(booking);

        List<BookingResponse> bookingResponses = new ArrayList<>();
        BookingResponse response = new BookingResponse();
        response.setId(id);
        response.setUser(user);
        bookingResponses.add(response);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(bookingRepository.findBookingsByUser(user)).thenReturn(bookings);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(response);

        List<BookingResponse> result = bookingService.getBookingsByUserId(user.getId());

        assertEquals(bookingResponses.size(), result.size());
        assertEquals(bookingResponses.get(0).getUser(), result.get(0).getUser());

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).findBookingsByUser(user);
    }

    @Test
    @DisplayName("Test find all bookings by user id, but user id not found")
    public void testGetBookingsByUserIdButUserIdNotFound() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.getBookingsByUserId(id);
        });

        assertEquals("User not found", thrown.getMessage());

        verify(userRepository, times(1)).findById(id);
        verify(bookingRepository, never()).findBookingsByUser(any(User.class));
    }

    @Test
    @DisplayName("Test find all bookings by car id")
    public void testGetBookingsByCarId() {
        Long id = 1L;

        Car car = new Car();
        car.setId(id);

        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(id);
        booking.setCar(car);
        bookings.add(booking);

        List<BookingResponse> bookingResponses = new ArrayList<>();
        BookingResponse response = new BookingResponse();
        response.setId(id);
        response.setCar(car);
        bookingResponses.add(response);

        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));
        Mockito.when(bookingRepository.findBookingsByCar(car)).thenReturn(bookings);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(response);

        List<BookingResponse> result = bookingService.getBookingsByCarId(car.getId());

        assertEquals(bookingResponses.size(), result.size());
        assertEquals(bookingResponses.get(0).getCar(), result.get(0).getCar());

        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).findBookingsByCar(car);
    }

    @Test
    @DisplayName("Test find all bookings by car id, but car id not found")
    public void testGetBookingsByCarIdButCarIdNotFound() {
        Long id = 1L;
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.getBookingsByCarId(id);
        });

        assertEquals("Car not found", thrown.getMessage());

        verify(carRepository, times(1)).findById(id);
        verify(bookingRepository, never()).findBookingsByCar(any(Car.class));
    }

    @Test
    @DisplayName("Test find all bookings by user id and car id")
    public void testGetBookingsByUserIdAndCarId() {
        Long id = 1L;

        User user = new User();
        user.setId(id);

        Car car = new Car();
        car.setId(id);

        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(id);
        booking.setCar(car);
        booking.setUser(user);
        bookings.add(booking);

        List<BookingResponse> bookingResponses = new ArrayList<>();
        BookingResponse response = new BookingResponse();
        response.setId(id);
        response.setCar(car);
        response.setUser(user);
        bookingResponses.add(response);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));
        Mockito.when(bookingRepository.findBookingsByUserAndCar(user, car)).thenReturn(bookings);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(response);

        List<BookingResponse> result = bookingService.getBookingsByUserIdAndCarId(user.getId(), car.getId());

        assertEquals(bookingResponses.size(), result.size());
        assertEquals(bookingResponses.get(0).getUser(), result.get(0).getUser());
        assertEquals(bookingResponses.get(0).getCar(), result.get(0).getCar());

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(carRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).findBookingsByUserAndCar(user, car);
    }

    @Test
    @DisplayName("Test find all bookings by user and car id, but user id not found")
    public void testGetBookingsByUserIdAndCarIdButUserIdNotFound() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.getBookingsByUserIdAndCarId(id, id);
        });

        assertEquals("User not found", thrown.getMessage());

        verify(userRepository, times(1)).findById(id);
        verify(bookingRepository, never()).findBookingsByUserAndCar(any(User.class), any(Car.class));
    }

    @Test
    @DisplayName("Test find all bookings by user and car id, but car id not found")
    public void testGetBookingsByUserIdAndCarIdButCarIdNotFound() {
        Long id = 1L;

        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Mockito.when(carRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.getBookingsByUserIdAndCarId(id, id);
        });

        assertEquals("Car not found", thrown.getMessage());

        verify(userRepository, times(1)).findById(id);
        verify(carRepository, times(1)).findById(id);
        verify(bookingRepository, never()).findBookingsByUserAndCar(any(User.class), any(Car.class));
    }

    @Test
    @DisplayName("Test find all bookings by dates")
    public void testGetBookingsByDates() {
        Long id = 1L;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        DateRequest request = new DateRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(id);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        bookings.add(booking);

        List<BookingResponse> bookingResponses = new ArrayList<>();
        BookingResponse response = new BookingResponse();
        response.setId(id);
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        bookingResponses.add(response);

        Mockito.when(bookingRepository.findByDate(startDate, endDate)).thenReturn(bookings);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(response);

        List<BookingResponse> result = bookingService.getBookingsByDates(request);

        assertEquals(bookingResponses.size(), result.size());

        Mockito.verify(bookingRepository, Mockito.times(1)).findByDate(startDate, endDate);
    }

    @Test
    @DisplayName("Test find booking by payment id")
    public void testGetBookingByPaymentId() {
        Long id = 1L;

        Payment payment = new Payment();
        payment.setId(id);

        Booking booking = new Booking();
        booking.setId(id);
        booking.setPayment(payment);

        BookingResponse expected = new BookingResponse();
        expected.setId(id);
        expected.setPayment(payment);

        Mockito.when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));
        Mockito.when(bookingRepository.findBookingByPayment(payment)).thenReturn(booking);
        Mockito.when(mapper.mapToResponse(booking)).thenReturn(expected);

        BookingResponse result = bookingService.getBookingByPaymentId(payment.getId());

        assertEquals(expected.getId(), result.getId());

        Mockito.verify(paymentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(bookingRepository, Mockito.times(1)).findBookingByPayment(payment);
    }

    @Test
    @DisplayName("Test find all bookings by payment id, but payment id not found")
    public void testGetBookingsByPaymentIdButPaymentIdNotFound() {
        Long id = 1L;
        Mockito.when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, ()-> {
            bookingService.getBookingByPaymentId(id);
        });

        assertEquals("Payment not found", thrown.getMessage());

        verify(paymentRepository, times(1)).findById(id);
        verify(bookingRepository, never()).findBookingByPayment(any(Payment.class));
    }
}

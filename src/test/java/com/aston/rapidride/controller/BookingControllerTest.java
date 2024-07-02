package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.BookingRequest;
import com.aston.rapidride.dto.request.DateRequest;
import com.aston.rapidride.dto.response.BookingResponse;
import com.aston.rapidride.service.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    private final BookingService bookingService = Mockito.mock(BookingService.class);

    private final BookingController bookingController = new BookingController(bookingService);

    @Test
    @DisplayName("Test find booking by id")
    public void testFindById() {
        Long id = 1L;
        BookingResponse expected = new BookingResponse();
        expected.setId(id);

        when(bookingService.getById(id)).thenReturn(expected);

        ResponseEntity<BookingResponse> result = bookingController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getById(id);
    }

    @Test
    @DisplayName("Test booking create")
    public void testCreate() {
        BookingRequest request = new BookingRequest();
        ResponseEntity<String> result = bookingController.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Successfully", result.getBody());

        verify(bookingService, times(1)).createBooking(request);
    }

    @Test
    @DisplayName("Test booking update")
    public void testUpdate() {
        Long id = 1L;
        BookingRequest request = new BookingRequest();
        BookingResponse expected = new BookingResponse();
        expected.setId(id);

        when(bookingService.updateBooking(id, request)).thenReturn(expected);

        ResponseEntity<BookingResponse> result = bookingController.update(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).updateBooking(id, request);
    }

    @Test
    @DisplayName("Test find all bookings")
    public void testFindAll() {
        List<BookingResponse> expected = new ArrayList<>();
        expected.add(new BookingResponse());

        when(bookingService.getAllBookings()).thenReturn(expected);

        ResponseEntity<List<BookingResponse>> result = bookingController.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    @DisplayName("Test find all bookings by status id")
    public void testFindAllByStatusId() {
        Long id = 1L;
        List<BookingResponse> expected = new ArrayList<>();
        expected.add(new BookingResponse());

        when(bookingService.getBookingsByStatusId(id)).thenReturn(expected);

        ResponseEntity<List<BookingResponse>> result = bookingController.findAllByStatusId(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getBookingsByStatusId(id);
    }

    @Test
    @DisplayName("Test find all bookings by user id")
    public void testFindAllByUserId() {
        Long id = 1L;
        List<BookingResponse> expected = new ArrayList<>();
        expected.add(new BookingResponse());

        when(bookingService.getBookingsByUserId(id)).thenReturn(expected);

        ResponseEntity<List<BookingResponse>> result = bookingController.findAllByUserId(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getBookingsByUserId(id);
    }

    @Test
    @DisplayName("Test find all bookings by car id")
    public void testFindAllByCarId() {
        Long id = 1L;
        List<BookingResponse> expected = new ArrayList<>();
        expected.add(new BookingResponse());

        when(bookingService.getBookingsByCarId(id)).thenReturn(expected);

        ResponseEntity<List<BookingResponse>> result = bookingController.findAllByCarId(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getBookingsByCarId(id);
    }

    @Test
    @DisplayName("Test find all bookings by user id and car id")
    public void testFindAllByUserAndCarId() {
        Long userId = 1L;
        Long carId = 1L;

        List<BookingResponse> expected = new ArrayList<>();
        expected.add(new BookingResponse());

        when(bookingService.getBookingsByUserIdAndCarId(userId, carId)).thenReturn(expected);

        ResponseEntity<List<BookingResponse>> result = bookingController.findAllByUserAndCarId(userId, carId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getBookingsByUserIdAndCarId(userId, carId);
    }

    @Test
    @DisplayName("Test find all bookings by dates")
    public void testFindAllByDates() {
        DateRequest request = new DateRequest();

        List<BookingResponse> expected = new ArrayList<>();
        expected.add(new BookingResponse());

        when(bookingService.getBookingsByDates(request)).thenReturn(expected);

        ResponseEntity<List<BookingResponse>> result = bookingController.findAllByDates(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getBookingsByDates(request);
    }

    @Test
    @DisplayName("Test find booking by payment id")
    public void testFindAllByPaymentId() {
        Long id = 1L;
        BookingResponse expected = new BookingResponse();

        when(bookingService.getBookingByPaymentId(id)).thenReturn(expected);

        ResponseEntity<BookingResponse> result = bookingController.findAllByPaymentId(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingService, times(1)).getBookingByPaymentId(id);
    }
}

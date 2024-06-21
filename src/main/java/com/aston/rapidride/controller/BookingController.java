package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.BookingRequest;
import com.aston.rapidride.dto.request.DateRequest;
import com.aston.rapidride.dto.response.BookingResponse;
import com.aston.rapidride.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(booking, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody BookingRequest request) {
        bookingService.createBooking(request);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable Long id, @RequestBody BookingRequest request) {
        return new ResponseEntity<>(bookingService.updateBooking(id, request), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BookingResponse>> findAll() {
        List<BookingResponse> bookings = bookingService.getAllBookings();
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @GetMapping("/statuses/{id}")
    public ResponseEntity<List<BookingResponse>> findAllByStatusId(@PathVariable Long id) {
        List<BookingResponse> bookings = bookingService.getBookingsByStatusId(id);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<BookingResponse>> findAllByUserId(@PathVariable Long id) {
        List<BookingResponse> bookings = bookingService.getBookingsByUserId(id);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<List<BookingResponse>> findAllByCarId(@PathVariable Long id) {
        List<BookingResponse> bookings = bookingService.getBookingsByCarId(id);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @GetMapping("/users/{userId}/cars/{carId}")
    public ResponseEntity<List<BookingResponse>> findAllByUserAndCarId(@PathVariable Long userId,
                                                                       @PathVariable Long carId) {
        List<BookingResponse> bookings = bookingService.getBookingsByUserIdAndCarId(userId, carId);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @GetMapping("/dates")
    public ResponseEntity<List<BookingResponse>> findAllByDates(@RequestBody DateRequest request) {
        List<BookingResponse> bookings = bookingService.getBookingsByDates(request);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<BookingResponse> findAllByPaymentId(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBookingByPaymentId(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(booking, HttpStatus.FOUND);
    }
}

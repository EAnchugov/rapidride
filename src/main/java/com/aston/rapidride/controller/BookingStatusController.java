package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.service.BookingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking-statuses")
public class BookingStatusController {

    private final BookingStatusService bookingStatusService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingStatusResponse> findById(@PathVariable Long id) {
        BookingStatusResponse bookingStatus = bookingStatusService.getById(id);
        if (bookingStatus == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookingStatus, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody BookingStatusRequest request) {
        bookingStatusService.createBookingStatus(request);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingStatusResponse> update(@PathVariable Long id,
                                                        @RequestBody BookingStatusRequest request) {
        return new ResponseEntity<>(bookingStatusService.updateBookingStatus(id, request), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<BookingStatusResponse>> findAll() {
        List<BookingStatusResponse> bookingStatuses = bookingStatusService.getAllBookingStatuses();
        if (bookingStatuses == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookingStatuses, HttpStatus.FOUND);
    }
}

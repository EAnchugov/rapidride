package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.service.BookingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking-statuses")
public class BookingStatusController {

    private final BookingStatusService bookingStatusService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookingStatusResponse> findById(@PathVariable Long id) {
        BookingStatusResponse bookingStatus = bookingStatusService.getById(id);
        return new ResponseEntity<>(bookingStatus, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> create(@Valid @RequestBody BookingStatusRequest request) {
        bookingStatusService.createBookingStatus(request);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookingStatusResponse> update(@PathVariable Long id,
                                                        @Valid @RequestBody BookingStatusRequest request) {
        return new ResponseEntity<>(bookingStatusService.updateBookingStatus(id, request), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BookingStatusResponse>> findAll() {
        List<BookingStatusResponse> bookingStatuses = bookingStatusService.getAllBookingStatuses();
        return new ResponseEntity<>(bookingStatuses, HttpStatus.OK);
    }
}

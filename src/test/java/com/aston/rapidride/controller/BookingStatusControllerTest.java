package com.aston.rapidride.controller;

import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.service.BookingStatusService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingStatusControllerTest {

    @Mock
    private final BookingStatusService bookingStatusService = Mockito.mock(BookingStatusService.class);

    @InjectMocks
    private BookingStatusController bookingStatusController;

    @Test
    @DisplayName("Test find booking status by id")
    void testFindById() {
        Long id = 1L;
        BookingStatusResponse bookingStatusResponse = new BookingStatusResponse();
        bookingStatusResponse.setId(id);

        when(bookingStatusService.getById(id)).thenReturn(bookingStatusResponse);

        ResponseEntity<BookingStatusResponse> responseEntity = bookingStatusController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bookingStatusResponse, responseEntity.getBody());

        verify(bookingStatusService, times(1)).getById(id);
    }

    @Test
    @DisplayName("Test booking status create")
    void testCreate() {
        BookingStatusRequest bookingStatusRequest = new BookingStatusRequest();
        ResponseEntity<String> result = bookingStatusController.create(bookingStatusRequest);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Successfully", result.getBody());

        verify(bookingStatusService, times(1)).createBookingStatus(bookingStatusRequest);
    }

    @Test
    @DisplayName("Test booking status update")
    void testUpdate() {
        Long id = 1L;
        BookingStatusRequest bookingStatusRequest = new BookingStatusRequest();
        BookingStatusResponse expected = new BookingStatusResponse();
        expected.setId(id);

        when(bookingStatusService.updateBookingStatus(id, bookingStatusRequest)).thenReturn(expected);

        ResponseEntity<BookingStatusResponse> result = bookingStatusController.update(id, bookingStatusRequest);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingStatusService, times(1)).updateBookingStatus(id, bookingStatusRequest);
    }

    @Test
    @DisplayName("Test find all booking statuses")
    void testFindAll() {
        List<BookingStatusResponse> expected = new ArrayList<>();
        expected.add(new BookingStatusResponse());

        when(bookingStatusService.getAllBookingStatuses()).thenReturn(expected);

        ResponseEntity<List<BookingStatusResponse>> result = bookingStatusController.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expected, result.getBody());

        verify(bookingStatusService, times(1)).getAllBookingStatuses();
    }
}

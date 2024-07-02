package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BookingStatusMapper;
import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.entity.BookingStatus;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.BookingStatusRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingStatusServiceImplTest {

    @Mock
    private BookingStatusRepository repository;

    @Mock
    private BookingStatusMapper mapper;

    @InjectMocks
    private BookingStatusServiceImpl service;

    @Test
    @DisplayName("Test find booking status by id")
    public void testGetById() {
        Long id = 1L;
        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setId(id);

        BookingStatusResponse expected = new BookingStatusResponse();
        expected.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(bookingStatus));
        Mockito.when(mapper.mapToResponse(bookingStatus)).thenReturn(expected);

        BookingStatusResponse result = service.getById(id);

        assertEquals(expected.getId(), result.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Test find booking status by id, but id not found")
    public void testGetByIdNotFound() {
        Long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            service.getById(id);
        });

        assertEquals("Booking status not found", thrown.getMessage());
        verify(repository, Mockito.times(1)).findById(id);
        verify(mapper, never()).mapToResponse(any(BookingStatus.class));
    }

    @Test
    @DisplayName("Test booking status create")
    public void testCreateBookingStatus() {
        BookingStatusRequest request = new BookingStatusRequest();
        request.setName("Created");

        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setName("Created");

        Mockito.when(mapper.mapToEntity(request)).thenReturn(bookingStatus);

        service.createBookingStatus(request);

        Mockito.verify(repository, Mockito.times(1)).save(bookingStatus);
    }

    @Test
    @DisplayName("Test booking status update")
    public void testUpdateBookingStatus() {
        Long id = 1L;
        BookingStatusRequest request = new BookingStatusRequest();
        request.setName("Updated");

        BookingStatusResponse expected = new BookingStatusResponse();
        expected.setId(id);

        BookingStatus bookingStatus = new BookingStatus();
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(bookingStatus));
        Mockito.when(mapper.mapToResponse(bookingStatus)).thenReturn(expected);

        BookingStatusResponse result = service.updateBookingStatus(id, request);

        assertEquals(expected, result);

        Mockito.verify(repository, Mockito.times(1)).save(bookingStatus);
    }

    @Test
    @DisplayName("Test booking status update, but id not found")
    public void testUpdateButIdNotFound() {
        Long id = 1L;
        BookingStatusRequest request = new BookingStatusRequest();

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            service.updateBookingStatus(1L, request);
        });

        assertEquals("Booking status not found", thrown.getMessage());
        verify(repository, Mockito.times(1)).findById(id);
        verify(repository, never()).save(any(BookingStatus.class));
        verify(mapper, never()).mapToResponse(any(BookingStatus.class));
    }

    @Test
    @DisplayName("Test find all booking statuses")
    public void testGetAllBookingStatuses() {
        Long id = 1L;
        List<BookingStatus> bookingStatuses = new ArrayList<>();
        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setId(id);
        bookingStatuses.add(bookingStatus);

        List<BookingStatusResponse> bookingStatusResponses = new ArrayList<>();
        BookingStatusResponse response = new BookingStatusResponse();
        response.setId(id);
        bookingStatusResponses.add(response);

        Mockito.when(repository.findAll()).thenReturn(bookingStatuses);
        Mockito.when(mapper.mapToResponse(bookingStatus)).thenReturn(response);

        List<BookingStatusResponse> result = service.getAllBookingStatuses();

        assertEquals(bookingStatusResponses.size(), result.size());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test find all booking statuses, but result list is empty")
    public void testFindAllEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<BookingStatusResponse> result = service.getAllBookingStatuses();

        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
        verify(mapper, never()).mapToResponse(any(BookingStatus.class));
    }
}

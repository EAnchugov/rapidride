package com.aston.rapidride.service;

import com.aston.rapidride.dto.mapper.BookingStatusMapper;
import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.entity.*;
import com.aston.rapidride.repository.BookingStatusRepository;
import com.aston.rapidride.service.impl.BookingStatusServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingStatusServiceTest {

    private final BookingStatusRepository repository = Mockito.mock(BookingStatusRepository.class);

    private final BookingStatusMapper mapper = Mockito.mock(BookingStatusMapper.class);

    private final BookingStatusService service = new BookingStatusServiceImpl(repository, mapper);

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
}

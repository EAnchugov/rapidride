package com.aston.rapidride.service.impl;

import com.aston.rapidride.dto.mapper.BookingStatusMapper;
import com.aston.rapidride.dto.request.BookingStatusRequest;
import com.aston.rapidride.dto.response.BookingStatusResponse;
import com.aston.rapidride.entity.BookingStatus;
import com.aston.rapidride.exception.NotFoundException;
import com.aston.rapidride.repository.BookingStatusRepository;
import com.aston.rapidride.service.BookingStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aston.rapidride.utility.TextConstants.BOOKING_STATUS_NOT_FOUND;

@Service
@AllArgsConstructor
public class BookingStatusServiceImpl implements BookingStatusService {

    private final BookingStatusRepository repository;
    private final BookingStatusMapper mapper;

    @Override
    public BookingStatusResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() -> new NotFoundException(BOOKING_STATUS_NOT_FOUND.get()));
    }

    @Override
    public void createBookingStatus(BookingStatusRequest request) {
        BookingStatus bookingStatus = mapper.mapToEntity(request);
        repository.save(bookingStatus);
    }

    @Override
    public BookingStatusResponse updateBookingStatus(Long id, BookingStatusRequest request) {
        BookingStatus bookingStatus = repository.findById(id).orElseThrow(()
                -> new NotFoundException(BOOKING_STATUS_NOT_FOUND.get()));
        bookingStatus.setName(request.getName());
        repository.save(bookingStatus);

        return mapper.mapToResponse(bookingStatus);
    }

    @Override
    public List<BookingStatusResponse> getAllBookingStatuses() {
        List<BookingStatus> bookingStatuses = repository.findAll();
        return bookingStatuses.stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }
}

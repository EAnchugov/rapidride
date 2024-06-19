package com.aston.rapidride.service;

import com.aston.rapidride.entity.*;

import java.util.List;

public interface BookingStatusService {

    BookingStatus getById(Long id);

    void createBookingStatus(BookingStatus fine);

    void updateBookingStatus(BookingStatus fine);

    List<BookingStatus> getAllBookingStatuses();

}

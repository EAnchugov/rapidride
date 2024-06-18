package com.aston.rapidride.repository;

import com.aston.rapidride.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingStatusRepository extends JpaRepository<BookingStatus, Integer> {
}

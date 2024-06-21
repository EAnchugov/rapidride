package com.aston.rapidride.repository;

import com.aston.rapidride.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingsByStatus(BookingStatus status);

    List<Booking> findBookingsByUser(User user);

    List<Booking> findBookingsByCar(Car car);

    Booking findBookingByPayment(Payment payment);

    List<Booking> findBookingsByUserAndCar(User user, Car car);

    @Query(value = "SELECT b.* FROM bookings b WHERE b.start_date >= :startDate AND b.end_date <= :endDate", nativeQuery = true)
    List<Booking> findByDate(@Param("startDate") LocalDate startDate,
                             @Param("endDate") LocalDate endDate);

}

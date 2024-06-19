package com.aston.rapidride.repository;

import com.aston.rapidride.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
//   @Query("select f from Fine f where f.userId = :id")
    List<Fine> findAllByUserId(Long id);

 //   @Query("select f from Fine f where f.carId = :id")
    List<Fine> findAllByCarId(Long id);

//    @Query("select f from Fine f where f.car = :carId and f.userId = :userId")
    Fine findAllByCarIdAndUserId(Long carId, Long userId);

    @Query("select f from Fine f where f.registrationNumber = :number")
    List<Fine> findAllByRegistrationNumber(Long number);

    @Query("select f from Fine f where f.sum = :sum")
    List<Fine> findAllBySum(BigDecimal sum);

    @Query("select f from Fine f where f.date = :date")
    List<Fine> findAllByDate(LocalDate date);
}

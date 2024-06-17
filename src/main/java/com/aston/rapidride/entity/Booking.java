package com.aston.rapidride.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO Сделать Car классом после добавления
    @NotBlank
    private Long carId;

    //TODO Сделать User классом после добавления
    @NotBlank
    private Long userId;

    @NotBlank
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @NotBlank
    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @NotBlank
    @Column(name = "total_amount", columnDefinition = "DECIMAL")
    private BigDecimal totalAmount;

    //TODO Сделать Payment классом после добавления
    @NotBlank
    private Long paymentId;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private BookingStatus status;

    @NotBlank
    @Column(columnDefinition = "DECIMAL")
    private BigDecimal price;

    @NotBlank
    private String comments;
}

package com.aston.rapidride.entity.DAO;

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

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotBlank
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @NotBlank
    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @NotBlank
    @Column(name = "total_amount", columnDefinition = "DECIMAL")
    private BigDecimal totalAmount;

    @NotBlank
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private BookingStatus status;

    @NotBlank
    private String comments;
}

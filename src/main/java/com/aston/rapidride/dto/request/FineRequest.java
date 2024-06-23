package com.aston.rapidride.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FineRequest {
        @Min(value = 1, message = "id должен быть больше 0")
        private Long id;
        @Min(value = 1, message = "id должен быть больше 0")
        private Long carId;
        @Min(value = 1, message = "id должен быть больше 0")
        private Long userId;
        @NotBlank(message = "дата должна быть заполненна")
        private LocalDate date;
        @NotBlank(message = "сумма должна быть заполненна")
        private BigDecimal sum;
        @NotBlank(message = "Номер регистрации на документ должен быть заполненн")
        private String registrationNumber;
        @NotBlank(message = "Ссылка на документ должна быть заполненна")
        private Long paymentId;
}

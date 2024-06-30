package com.aston.rapidride.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO для отображения курса валюты
 *
 * @author Azimov Ruslan
 */
@Getter
@Setter
public class CurrencyDto {
    @JsonProperty("Cur_Name")
    private String curName;
    @JsonProperty("Cur_OfficialRate")
    private BigDecimal curOfficialRate;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
}

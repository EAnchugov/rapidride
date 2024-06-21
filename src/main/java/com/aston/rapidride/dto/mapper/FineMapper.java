package com.aston.rapidride.dto.mapper;

import com.aston.rapidride.dto.request.FineRequest;
import com.aston.rapidride.dto.response.FineResponce;
import com.aston.rapidride.entity.Fine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FineMapper {
    public static Fine toFine(FineRequest fineRequest) {
        return new Fine();
    }

    public static FineRequest toFineRequest(Fine fine) {
        return new FineRequest();
    }

    public static FineResponce toFineResponce(Fine fine) {
        return new FineResponce();
    }
}

package com.acledabankplc.baseResponse;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BaseError<T>(
        Boolean status,
        Integer code,
        String messages,
        LocalDate timeStamp,
        T errors

) {
}
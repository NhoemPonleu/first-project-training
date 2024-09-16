package com.acledabankplc.baseResponse;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BaseError<T>(
        Boolean status,
        Integer code,
        String messages,
        LocalDate timeStamp,
        T errors

) {
}
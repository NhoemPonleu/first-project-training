package com.acledabankplc.baseResponse;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BaseApi<T>(
        String message,
        Integer code,
        Boolean status,
        LocalDate timeStamp,
        T data
) {

}
package com.example.startupapi.domain.register;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(200),
    ALREADY_REGISTERED(409),
    BAD_REQUEST(400);

    private final int code;
}

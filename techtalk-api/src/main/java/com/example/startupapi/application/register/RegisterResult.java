package com.example.startupapi.application.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResult {
    private long processId;
    private boolean isSuccess;
    private String resultMessage;
    private int resultCode;
    private String username;
}

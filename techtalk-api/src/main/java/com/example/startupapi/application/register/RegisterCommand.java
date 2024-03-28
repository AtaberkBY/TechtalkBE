package com.example.startupapi.application.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterCommand {
    private long processId;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}

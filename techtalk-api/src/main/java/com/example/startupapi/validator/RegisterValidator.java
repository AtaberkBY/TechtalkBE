package com.example.startupapi.validator;

import com.example.startupapi.application.register.RegisterCommand;
import org.springframework.stereotype.Component;

@Component
public class RegisterValidator {

    public String isUserValid(RegisterCommand registerCommand){

        if(registerCommand.getUsername().length() < 5 || registerCommand.getUsername().length() > 20){
           return "Kullanıcı isminiz en az 6, en fazla 20 haneli olmalıdır";
        }
        if(registerCommand.getPassword().length() < 6 || registerCommand.getPassword().length() > 35 || registerCommand.getPassword().contains()){

        }
    }
}

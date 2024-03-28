package com.example.startupapi.services.assembler;

import com.example.startupapi.application.register.RegisterCommand;
import com.example.startupapi.application.register.RegisterResult;
import com.example.startupapi.domain.register.ResultCode;
import org.springframework.stereotype.Component;

@Component
public class RegisterResultAssembler {

    public RegisterResult applyAlreadyExistFailure(RegisterCommand registerCommand){
        RegisterResult result = new RegisterResult();
        result.setResultCode(ResultCode.ALREADY_REGISTERED.getCode());
        result.setResultMessage("Kayıtlı kullanıcı register istegi alindi"); //TODO Değiştir.
        result.setSuccess(false);
        result.setUsername(registerCommand.getUsername());
        result.setProcessId(registerCommand.getProcessId());

        return result;
    }

    public RegisterResult applyInvalidRequestFailure(RegisterCommand registerCommand, String validationMessage){
        RegisterResult result = new RegisterResult();
        result.setResultCode(ResultCode.BAD_REQUEST.getCode());
        result.setResultMessage(validationMessage);
        result.setSuccess(false);
        result.setUsername(registerCommand.getUsername());
        result.setProcessId(registerCommand.getProcessId());

        return result;
    }

    public RegisterResult applySuccessResult(RegisterCommand registerCommand) {
        RegisterResult result = new RegisterResult();
        result.setResultCode(ResultCode.SUCCESS.getCode());
        result.setResultMessage("");
        result.setSuccess(true);
        result.setUsername(registerCommand.getUsername());
        result.setProcessId(registerCommand.getProcessId());

        return result;
    }
}

package com.example.startupapi.services;

import com.example.startupapi.application.login.LoginCommand;
import com.example.startupapi.application.login.LoginResult;
import com.example.startupapi.application.register.RegisterCommand;
import com.example.startupapi.application.register.RegisterResult;
import com.example.startupapi.data.UserRepository;
import com.example.startupapi.domain.user.User;
import com.example.startupapi.domain.user.UserRole;
import com.example.startupapi.services.assembler.RegisterResultAssembler;
import com.example.startupapi.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RegisterResultAssembler registerResultAssembler;
    private final RegisterValidator registerValidator;

    public RegisterResult register(RegisterCommand registerCommand) {

        if (userRepository.existsByEmailOrUsername(registerCommand.getEmail(), registerCommand.getUsername())) {
            log.info("Kayıtlı kullanıcı kaydetme isteği alındı. ProcessId: {}, Username: {}, Email: {}",
                    registerCommand.getProcessId(),
                    registerCommand.getUsername(),
                    registerCommand.getEmail());

            return registerResultAssembler.applyAlreadyExistFailure(registerCommand);
        }

        String validationMessage = registerValidator.isUserValid(registerCommand);

        if (!validationMessage.isBlank()) {
            return registerResultAssembler.applyInvalidRequestFailure(registerCommand, validationMessage);
        }

        User user = prepareUser(registerCommand);
        userRepository.save(user);

        return registerResultAssembler.applySuccessResult(registerCommand);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("auth.user-with-email.not.found"));
        } catch (Exception e) {
            log.info("User not found by email: {}", email);
            return null;
        }
    }

    private User prepareUser(RegisterCommand registerCommand) {
        return new User(
                registerCommand.getName(),
                registerCommand.getSurname(),
                registerCommand.getUsername(),
                registerCommand.getPassword(),
                registerCommand.getEmail(),
                UserRole.USER,
                false,
                true
        );
    }

    public LoginResult login(LoginCommand loginCommand) {
        return null;
    }
}

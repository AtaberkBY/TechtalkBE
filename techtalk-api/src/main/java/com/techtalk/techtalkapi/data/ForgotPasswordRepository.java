package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.forgotpassword.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
   Optional <ForgotPassword> findByToken(String token);

}

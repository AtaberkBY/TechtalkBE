package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.forgotpassword.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

}

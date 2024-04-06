package com.techtalk.techtalkapi.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

@Configuration
public class MessageConfig {

    private static MessageSource messageSource;

    public MessageConfig(MessageSource messageSource) {
        MessageConfig.messageSource = messageSource;
    }

    public static String get(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}

package com.jesusfc.springBootSentryLogback.config;

import io.sentry.Hint;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 * Email: jfcaraballo@gmail.com
 */
@Slf4j
@Configuration
public class SentryConfig implements SentryOptions.BeforeSendCallback {

    private static final String ONLY_ENVIRONMENT_WORKS = "production";

    @Value("${sentry.dsn}")
    private String dsn;

    @Value("${spring.config.activate.on-profile}")
    private String environment;

    @Value("${sentry.release}")
    private String release;

    @Bean
    public String sentryInit() {
        Sentry.init(options -> {
            options.setDsn(dsn);
            options.setEnvironment(environment);
            options.setRelease(release);
            options.setBeforeSend(this);
        });
        return null;
    }

    @Nullable
    @Override
    public SentryEvent execute(@Nullable SentryEvent sentryEvent, @Nullable Hint hint) {

        assert sentryEvent != null;
        if (Objects.equals(sentryEvent.getEnvironment(), ONLY_ENVIRONMENT_WORKS)) return sentryEvent;
        log.error("ERROR: ", sentryEvent.getThrowable());
        return null;
    }

}